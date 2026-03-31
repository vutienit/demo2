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

    @DeleteMapping("/delete-all")
    public void deleteAll() {
        itemService.deleteAll();
    }

    @PostMapping("/test-transaction")
    public void test_transaction(@RequestParam(value = "param") int param) throws Exception {
        switch (param) {
            case 1 :
                itemService.testTransactionCase_1();
                break;
            case 2 :
                itemService.testTransactionCase_2();
                break;
            case 3 :
                itemService.testTransactionCase_3();
                break;
            case 4 :
                itemService.testTransactionCase_4();
                break;
            case 5 :
                itemService.testTransactionCase_5();
                break;
            case 6 :
                itemService.testTransactionCase_6();
                break;
            default:
                System.out.println("Nothing happened....");
                break;
        }
    }

    @PostMapping("/test-thread")
    public void test_thread() throws Exception {
        itemService.test_thread();
    }
}
