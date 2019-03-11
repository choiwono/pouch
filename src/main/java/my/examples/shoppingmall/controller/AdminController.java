package my.examples.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.FileImage;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.domain.ProductCategory;
import my.examples.shoppingmall.service.AdminService;
import my.examples.shoppingmall.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.io.File;
import java.util.UUID;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductCategoryService categoryService;
    private final AdminService adminService;

    @GetMapping("/write")
    public String writeform(Model model){
        List<ProductCategory> categories = categoryService.getFindAll();
        model.addAttribute("categories",categories);
        return "admin/writeform";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam(name="name") String name,
            @RequestParam(name="amount") Integer amount,
            @RequestParam(name="categoryId") Long categoryId,
            @RequestParam(name="image") MultipartFile[] images,
            @RequestParam(name="price") Integer price
            ){
        Assert.hasText(name, "물건이름을 입력해주세요");

        Product product = new Product();
        product.setName(name);
        product.setAmount(amount);
        product.setPrice(price);

        if(images != null && images.length > 0) {
            for(MultipartFile image : images) {
                FileImage fileImage = new FileImage();
                fileImage.setLength(image.getSize());
                fileImage.setOptions(1);
                fileImage.setMimeType(image.getContentType());
                fileImage.setFileName(image.getOriginalFilename());
                String saveFileName = saveFile(image);

                fileImage.setSaveFileName(saveFileName);
                product.addFileImage(fileImage);
            }
        }
        adminService.addProduct(product,categoryId);
        return "redirect:/main";
    }

    private String saveFile(MultipartFile image){
        String dir = "/tmp/";
        Calendar calendar = Calendar.getInstance();
        dir = dir + calendar.get(Calendar.YEAR);
        dir = dir + "/";
        dir = dir + (calendar.get(Calendar.MONTH) + 1);
        dir = dir + "/";
        dir = dir + calendar.get(Calendar.DAY_OF_MONTH);
        dir = dir + "/";

        File dirFile = new File(dir);
        dirFile.mkdirs();
        dir = dir + UUID.randomUUID().toString();

        try(FileOutputStream fos = new FileOutputStream(dir);
            InputStream in = image.getInputStream()
        ){
            byte[] buffer = new byte[1024];
            int readCount = 0;
            while((readCount = in.read(buffer)) != -1){
                fos.write(buffer,0,readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return dir;
    }
}
