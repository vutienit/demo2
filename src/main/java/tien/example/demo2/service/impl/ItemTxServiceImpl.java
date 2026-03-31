package tien.example.demo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tien.example.demo2.domain.Item;
import tien.example.demo2.mapper.ItemMapper;
import tien.example.demo2.repository.ItemRepository;
import tien.example.demo2.repository.ItemRepositoryNative;
import tien.example.demo2.service.ItemTxService;

import java.math.BigDecimal;

@Service
public class ItemTxServiceImpl implements ItemTxService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionRequiredNew() {
        Item item = new Item();
        item.setName("transaction Required New");
        item.setPrice(new BigDecimal(1000000));
        itemRepository.save(item);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transactionRequiredNewException() {
        Item item = new Item();
        item.setName("transaction Required New Exception");
        item.setPrice(new BigDecimal(1000000));
        itemRepository.save(item);
        Integer k = null;
        int j = k;
        System.out.println(j);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void transactionNested() {
        Item item = new Item();
        item.setName("transaction Nested");
        item.setPrice(new BigDecimal(1000000));
        itemRepository.save(item);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void transactionNestedException() {
        Item item = new Item();
        item.setName("transaction Nested Exception");
        item.setPrice(new BigDecimal(1000000));
        itemRepository.save(item);
        Integer k = null;
        int j = k;
        System.out.println(j);
    }
}
