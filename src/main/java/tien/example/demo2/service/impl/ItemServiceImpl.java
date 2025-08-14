package tien.example.demo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import tien.example.demo2.domain.Item;
import tien.example.demo2.dto.ItemDTO;
import tien.example.demo2.mapper.ItemMapper;
import tien.example.demo2.repository.ItemRepository;
import tien.example.demo2.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    @Transactional
    public ItemDTO save(ItemDTO dto) {
        Item item = itemRepository.save(itemMapper.toEntity(dto));
        return itemMapper.toDto(item);
    }

    @Override
    public Page<ItemDTO> findByName(String name, Pageable pageable) {
        return itemRepository.findByName(name, pageable);
    }

    @Override
    public Page<ItemDTO> findAllPaging(Pageable pageable) {
        return itemRepository.findAllPaging(pageable);
    }

    @Override
    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }


}
