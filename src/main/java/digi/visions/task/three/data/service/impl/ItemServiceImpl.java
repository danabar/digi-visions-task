package digi.visions.task.three.data.service.impl;

import digi.visions.task.three.data.entity.Item;
import digi.visions.task.three.data.repository.ItemRepository;
import digi.visions.task.three.data.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void save(Item folder) {
        itemRepository.save(folder);
    }
}
