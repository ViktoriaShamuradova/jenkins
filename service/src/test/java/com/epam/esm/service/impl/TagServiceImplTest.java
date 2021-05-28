package com.epam.esm.service.impl;

import com.epam.esm.criteria_info.TagCriteriaInfo;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.persistence.TagDao;
import com.epam.esm.service.exception.NoSuchResourceException;
import com.epam.esm.service.modelmapper.GenericMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceImplTest {
    @InjectMocks
    private TagServiceImpl tagService;
    @Mock
    private TagDao tagDAO;
    @Mock
    private GenericMapper<TagDto, Tag> mapper;

    @Test
    public void find_shouldFindPageOfTags() {
        //given
        List<Tag> tags = new ArrayList<>();
        Tag t = new Tag();
        tags.add(t);

        TagCriteriaInfo criteriaInfo = new TagCriteriaInfo();
        Page<Tag> tagPage = new PageImpl<>(tags);
        Pageable pageRequest = PageRequest.of(0, 1);

        when(tagDAO.findAll(any(), any()))
                .thenReturn(tagPage);
        //when
        tagService.find(pageRequest, criteriaInfo);
        //then
        verify(tagDAO)
                .findAll(criteriaInfo, pageRequest);
        verify(mapper).toDTO(t);
    }

    @Test
    public void create_NotCreate() {
        Optional<Tag> t = Optional.of(new Tag());
        TagDto tagDTO = new TagDto();
        tagDTO.setName("AA");
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        TagCriteriaInfo tagCriteriaInfo = new TagCriteriaInfo();
        tagCriteriaInfo.setName(tagDTO.getName());

        when(tagDAO.find(tagCriteriaInfo)).thenReturn(t);

        tagService.create(tagDTO);

        verify(tagDAO).find(tagCriteriaInfo);
        verify(mapper).toDTO(t.get());
    }

    @Test
    public void create_Create() {
        //given
        TagDto tagDTO = new TagDto();
        tagDTO.setName("AA");
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        TagCriteriaInfo tagCriteriaInfo = new TagCriteriaInfo();
        tagCriteriaInfo.setName(tagDTO.getName());

        when(tagDAO.find(tagCriteriaInfo)).thenReturn(Optional.empty());
        when(mapper.toEntity(tagDTO)).thenReturn(tag);

        tagService.create(tagDTO);

        verify(tagDAO).find(tagCriteriaInfo);
        verify(mapper).toEntity(tagDTO);
        verify(tagDAO).save(tag);
    }

    @Test
    public void findById_shouldFind() {
        Tag t = new Tag();
        t.setId(1L);
        when(tagDAO.findById(anyLong())).thenReturn(Optional.of(t));
        tagService.findById(t.getId());
        verify(tagDAO).findById(t.getId());
        verify(mapper).toDTO(t);
    }

    @Test
    public void findById_shouldThrowException() {
        when(tagDAO.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(()
                -> tagService.findById(anyLong()))
                .isInstanceOf(NoSuchResourceException.class);
    }

    @Test
    public void findByString_shouldFind() {
        Tag t = new Tag();
        TagDto tagDTO = new TagDto();
        tagDTO.setName("AA");
        t.setName("AA");

        when(tagDAO.find(any())).thenReturn(Optional.of(t));
        when(mapper.toDTO(t)).thenReturn(tagDTO);
        tagService.find(t.getName());
        verify(tagDAO).find(any());
        verify(mapper).toDTO(t);

        assertThat(tagService.find(anyString()).equals(tagDTO));
    }

    @Test
    public void findByName_shouldThrowException() {
        when(tagDAO.find(any())).thenReturn(Optional.empty());
        assertThatThrownBy(()
                -> tagService.find(anyString()))
                .isInstanceOf(NoSuchResourceException.class);
    }

    @Test
    public void deleteByString_shouldDelete() {
        Tag t = new Tag();
        t.setName("A");
        Optional<Tag> optionalTag = Optional.of(t);
        when(tagDAO.find(any())).thenReturn(optionalTag);

        tagService.delete(t.getName());

        verify(tagDAO).find(any());
        verify(tagDAO).delete(t);
        assertThat(tagService.delete(anyString()));
    }

    @Test
    public void getMostUsedTagOfUserWithHighestCostOfOrders_shouldGet() {
        Map<Tag, Integer> map = new HashMap<>();
        Tag t = new Tag();
        Tag t2 = new Tag();
        map.put(t, 1);
        map.put(t2, 2);

        List<Tag> tags = new ArrayList<>();
        tags.add(t2);

        when(tagDAO.getTagsOfUserWithHighestCostOfOrders()).thenReturn(map);

        tagService.getMostUsedTagOfUserWithHighestCostOfOrders();

        assertThat(tagService.getMostUsedTagOfUserWithHighestCostOfOrders().size()
                == tags.size());
    }

    @Test
    public void update_shouldThrownException() {
        assertThatThrownBy(() -> tagService.update(new TagDto()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void delete_shouldThrownException() {
        assertThatThrownBy(() -> tagService.delete(1L))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}