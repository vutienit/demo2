package tien.example.demo2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tien.example.demo2.dto.ItemDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryNative {

    @PersistenceContext
    private EntityManager em;

    public Page<ItemDTO> findByName(String name, Pageable pageable) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("select i.id, i.name, i.price\n");
        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append("select count(i.id)\n");

        StringBuilder sql = new StringBuilder();
        sql.append("from Item i\n");
        if (!StringUtils.isEmpty(name)) {
            sql.append("where i.name like lower(CONCAT('%', :name, '%'))");
        }
        sqlSelect.append(sql);
        sqlCount.append(sql);

        Query querySelect = em.createNativeQuery(sqlSelect.toString());
        Query queryCount = em.createNativeQuery(sqlCount.toString());
        if (!StringUtils.isEmpty(name)) {
            querySelect.setParameter("name", name.toLowerCase());
            queryCount.setParameter("name", name.toLowerCase());
        }

        if(pageable.isPaged()) {
            int pageNumber = pageable.getPageNumber();
            int pageSize = pageable.getPageSize();
            querySelect.setFirstResult(pageNumber * pageSize);
            querySelect.setMaxResults(pageSize);
            List<Object[]> lstObject = querySelect.getResultList();
            BigInteger total = (BigInteger) queryCount.getSingleResult();
            if (!CollectionUtils.isEmpty(lstObject)) {
                List<ItemDTO> lstDTO = new ArrayList<>();
                for (Object[] data : lstObject) {
                    lstDTO.add(ItemDTO.builder()
                            .id(data[0] != null ? ((BigInteger) data[0]).longValue() : null)
                            .name(data[1] != null ? String.valueOf(data[1]) : null)
                            .price(data[2] != null ? (BigDecimal)data[2] : null)
                            .build());
                }
                return new PageImpl<>(lstDTO, pageable, total.longValue());
            }
        }
        return new PageImpl<>(new ArrayList<>(), pageable, 0);
    }
}
