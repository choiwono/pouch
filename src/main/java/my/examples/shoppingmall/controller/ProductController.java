package my.examples.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.FileImage;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.repository.ProductRepository;
import my.examples.shoppingmall.service.FileImageService;
import my.examples.shoppingmall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.webflow.view.AjaxThymeleafViewResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final FileImageService fileImageService;

    @GetMapping("/view")
    public String view(
            @RequestParam(name="id",
                    required=false,defaultValue="false") Long id,
            Model model){
        Product product = productRepository.findProductById(id);
        model.addAttribute("product",product);
        return "product/view";
    }

    @GetMapping("/list")
    public String list(
            @RequestParam(name="searchStr",required = false, defaultValue = "") String searchStr,
            @RequestParam(name="page",required = false, defaultValue = "1") int page,
            Model model){
        int limit = 10;
        int start = page * limit - limit;
        List<Product> products = productRepository.getProducts(start,limit,searchStr);
        long totalCount = productRepository.getProductsCount(searchStr);
        long totalPage = totalCount / 10;
        if(totalCount % 10 > 0){
            totalPage++;
        }

        model.addAttribute("products",products);
        model.addAttribute("searchStr",searchStr);
        model.addAttribute("page",page);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPage",totalPage);
        return "product/list";
    }

    @GetMapping("/images/{id}")
    @ResponseBody // 컨트롤러안에서 직접 response를 이용하여 결과를 출력할 때 사용
    public void downloadImage(
            @PathVariable(name = "id") Long id,
            HttpServletResponse response
    ) {
        FileImage fileImage = fileImageService.getFileImage(id);
        response.setContentType(fileImage.getMimeType());

        try(FileInputStream fis = new FileInputStream(fileImage.getSaveFileName());
            OutputStream out = response.getOutputStream()
        ){
            byte[] buffer = new byte[1024];
            int readCount = 0;

            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
