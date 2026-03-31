package tien.example.demo2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tien.example.demo2.dto.ItemDTO;

public interface ItemService {
    ItemDTO save(ItemDTO dto);
    Page<ItemDTO> findByName(String name, Pageable pageable);
    Page<ItemDTO> findAllPaging(Pageable pageable);
    void deleteAll();
    void testTransactionCase_1() throws Exception;
    void testTransactionCase_2() throws Exception;
    void testTransactionCase_3() throws Exception;
    void testTransactionCase_4() throws Exception;
    void testTransactionCase_5() throws Exception;
    void testTransactionCase_6() throws Exception;
    void test_thread() throws Exception;

}
