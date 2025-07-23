package tien.example.demo2.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tien.example.demo2.dto.ItemDTO;
import tien.example.demo2.service.ItemService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/item")
public class ItemResource {

    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ItemDTO save(@RequestBody @Valid ItemDTO dto) {
        return itemService.save(dto);
    }

    @GetMapping("/find-all")
    public Page<ItemDTO> findAllPaging(Pageable pageable) {
        return itemService.findAllPaging(pageable);
    }

    @GetMapping("/find-by-name")
    public Page<ItemDTO> findByName(@RequestParam(value = "name") String name, Pageable pageable) {
        return itemService.findByName(name, pageable);
    }
}
