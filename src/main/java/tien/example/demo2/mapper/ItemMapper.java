package tien.example.demo2.mapper;

import org.mapstruct.*;
import tien.example.demo2.domain.Item;
import tien.example.demo2.dto.ItemDTO;

@Mapper(componentModel = "spring", uses = {})
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

}
