package cychat.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ImageSave extends CrudRepository<Image,Integer> {

}
