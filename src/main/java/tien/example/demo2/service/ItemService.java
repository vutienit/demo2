package tien.example.demo2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tien.example.demo2.dto.ItemDTO;
import java.util.List;

public interface ItemService {
    ItemDTO save(ItemDTO dto);
    Page<ItemDTO> findByName(String name, Pageable pageable);
    Page<ItemDTO> findAllPaging(Pageable pageable);
    List<ItemDTO> findAll();
}
