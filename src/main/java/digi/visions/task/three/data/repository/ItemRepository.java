package digi.visions.task.three.data.repository;

import digi.visions.task.three.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
}

