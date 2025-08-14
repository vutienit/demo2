package tien.example.demo2.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tien.example.demo2.dto.ItemDTO;
import tien.example.demo2.service.ItemService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemQueryResolver implements GraphQLQueryResolver {

    private final ItemService itemService;

    public List<ItemDTO> items() {
        return itemService.findAll();
    }
}

