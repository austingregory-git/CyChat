package cychat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import cychat.image.Image;
import cychat.image.ImageSave;

@SpringBootApplication
public class UserDataApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UserDataApplication.class, args);
	}
	
//	
//	public void run(String... arg0) throws Exception
//	{
//		// image 1
//				ClassPathResource backImgFile = new ClassPathResource("image/chart1.png");
//				byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
//				backImgFile.getInputStream().read(arrayPic);
//				Image blackImage = new Image(1, "chart1", "png", arrayPic);
//				
//				// image 2
//				ClassPathResource blueImgFile = new ClassPathResource("image/chart2.png");
//				arrayPic = new byte[(int) blueImgFile.contentLength()];
//				blueImgFile.getInputStream().read(arrayPic);
//				Image blueImage = new Image(2, "char2", "png", arrayPic);
//				
//				// store image to MySQL via SpringJPA
//				IS.save(blackImage);
//				IS.save(blueImage);
//				
//				// retrieve image from MySQL via SpringJPA
////				for(Image imageModel : imageRepository.findAll()){
////					Files.write(Paths.get("retrieve-dir/" + imageModel.getName() + "." + imageModel.getType()), imageModel.getPic());
////				}
////			}
//	}
}
