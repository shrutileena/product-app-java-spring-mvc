package productapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productapp.entity.Product;
import productapp.service.ProductService;

@Controller
public class MainController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String home(Model model) {

		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);

		return "index";
	}

	@RequestMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("title", "Add Product");
		return "add_product_form";
	}

	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, Model model, HttpServletRequest request,
			BindingResult bindingResult) {

		Long id = this.productService.createProduct(product);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");

		System.out.println("Added product - " + id);
		return redirectView;
	}

	@RequestMapping(value = "/delete-product/{id}")
	public RedirectView deleteHandler(HttpServletRequest request, @PathVariable("id") Long id) {

		this.productService.deleteProduct(id);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");

		System.out.println("deleted product - " + id);
		return redirectView;
	}

	@RequestMapping(value = "/update-product/{id}")
	public String updateHandler(@PathVariable("id") Long id, Model model) {
		Product p = this.productService.getProduct(id);
		model.addAttribute("product", p);
		return "update_product";
	}

	@RequestMapping(value = "/update-product/update/{id}")
	public RedirectView updateProduct(@PathVariable("id") Long id, HttpServletRequest request,
			@ModelAttribute Product product, Model model) {

		this.productService.updateProduct(product);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");

		System.out.println("updated product - " + id);
		return redirectView;
	}
}
