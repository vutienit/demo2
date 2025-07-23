package tien.example.demo2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tien.example.demo2.domain.Item;
import tien.example.demo2.dto.ItemDTO;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select new tien.example.demo2.dto.ItemDTO(i.id, i.name, i.price) from Item i")
    Page<ItemDTO> findAllPaging(Pageable pageable);

    @Query(value = "select new tien.example.demo2.dto.ItemDTO(i.id, i.name, i.price) from Item i where i.name like CONCAT('%', :name, '%')")
    Page<ItemDTO> findByName(@Param("name") String name, Pageable pageable);
}
