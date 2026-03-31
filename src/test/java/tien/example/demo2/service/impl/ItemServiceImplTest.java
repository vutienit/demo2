//package tien.example.demo2.service.impl;
//
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import tien.example.demo2.domain.Item;
//import tien.example.demo2.dto.ItemDTO;
//import tien.example.demo2.mapper.ItemMapper;
//import tien.example.demo2.repository.ItemRepository;
//import tien.example.demo2.repository.ItemRepositoryNative;
//import tien.example.demo2.service.ItemService;
//import tien.example.demo2.service.ItemTxService;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ItemServiceImplTest {
//    @Mock
//    ItemRepository itemRepository;
//    @Mock
//    ItemRepositoryNative itemRepositoryNative;
//    @Mock
//    ItemMapper itemMapper;
//    @Mock
//    ItemTxService itemTxService;
//
//    @InjectMocks
//    ItemService itemService;
//
//    @Test
//    void save_validDto_returnsSavedDto() {
//        // given
//        ItemDTO input = ItemDTO.builder().name("A").price(new BigDecimal("100")).build();
//        Item entity = new Item();
//        entity.setName("A");
//        entity.setPrice(new BigDecimal("100"));
//
//        Item saved = new Item();
//        saved.setId(10L);
//        saved.setName("A");
//        saved.setPrice(new BigDecimal("100"));
//
//        ItemDTO output = ItemDTO.builder().id(10L).name("A").price(new BigDecimal("100")).build();
//
//        when(itemMapper.toEntity(input)).thenReturn(entity);
//        when(itemRepository.save(entity)).thenReturn(saved);
//        when(itemMapper.toDto(saved)).thenReturn(output);
//
//        // when
//        ItemDTO result = itemService.save(input);
//
//        // then
//        assertNotNull(result);
//        assertEquals(10L, result.getId());
//        assertEquals("A", result.getName());
//
//        verify(itemMapper).toEntity(input);
//        verify(itemRepository).save(entity);
//        verify(itemMapper).toDto(saved);
//        verifyNoMoreInteractions(itemRepository, itemMapper);
//    }
//}
