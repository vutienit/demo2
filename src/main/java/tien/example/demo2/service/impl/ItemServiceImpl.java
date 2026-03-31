package tien.example.demo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tien.example.demo2.domain.Item;
import tien.example.demo2.dto.ItemDTO;
import tien.example.demo2.mapper.ItemMapper;
import tien.example.demo2.repository.ItemRepository;
import tien.example.demo2.repository.ItemRepositoryNative;
import tien.example.demo2.service.ItemService;
import tien.example.demo2.service.ItemTxService;

import java.math.BigDecimal;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemRepositoryNative itemRepositoryNative;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemTxService itemTxService;

    @Override
    @Transactional
    public ItemDTO save(ItemDTO dto) {
        Item item = itemRepository.save(itemMapper.toEntity(dto));
        return itemMapper.toDto(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemDTO> findByName(String name, Pageable pageable) {
        return itemRepositoryNative.findByName(name, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItemDTO> findAllPaging(Pageable pageable) {
        return itemRepository.findAllPaging(pageable);
    }

    @Override
    @Transactional
    public void deleteAll() {
        itemRepository.deleteAll();
    }

    @Override
    @Transactional
    public void testTransactionCase_1() throws Exception {
        try {
            saveItem1();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            itemTxService.transactionRequiredNewException();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            saveItem2();
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    @Override
    @Transactional
    public void testTransactionCase_2() throws Exception {
        try {
            saveItem1();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            itemTxService.transactionNestedException();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            saveItem2();
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    @Override
    @Transactional
    public void testTransactionCase_3() throws Exception {

        try {
            itemTxService.transactionRequiredNew();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            saveItemException();
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    @Override
    @Transactional
    public void testTransactionCase_4() throws Exception {
        try {
            itemTxService.transactionNested();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            saveItemException();
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    @Override
    @Transactional
    public void testTransactionCase_5() throws Exception {
        try {
            saveItem1();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            itemTxService.transactionRequiredNew();
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    @Override
    @Transactional
    public void testTransactionCase_6() throws Exception {
        try {
            saveItem1();
        } catch (Exception e) {
            throw new Exception("");
        }

        try {
            itemTxService.transactionNested();
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    @Override
    @Transactional
    public void test_thread() throws Exception {
        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                sleep(100);
                synchronized (lockB) {
                    System.out.println("Thread 1 done");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                sleep(100);
                synchronized (lockA) {
                    System.out.println("Thread 2 done");
                }
            }
        });

        t1.start();
        t2.start();
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    void saveItem1() {
        Item item = new Item();
        item.setName("transaction normal 1");
        item.setPrice(new BigDecimal(1000000));
        itemRepository.save(item);
    }

    void saveItem2() {
        Item item = new Item();
        item.setName("transaction normal 2");
        item.setPrice(new BigDecimal(1000000));
        itemRepository.save(item);
    }

    void saveItemException() {
        Item item = new Item();
        item.setName("transaction normal");
        item.setPrice(new BigDecimal(1000000));
        Integer k = null;
        int j = k;
        System.out.println(j);
        itemRepository.save(item);
    }
}
