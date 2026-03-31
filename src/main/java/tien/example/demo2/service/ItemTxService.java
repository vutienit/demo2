package tien.example.demo2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tien.example.demo2.dto.ItemDTO;

public interface ItemTxService {
    void transactionRequiredNew();
    void transactionRequiredNewException();
    void transactionNested();
    void transactionNestedException();
}
