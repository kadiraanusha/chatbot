package com.example.demo.Productscontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Product;
import com.example.demo.entity.ServiceStatus;
import com.example.demo.exception.MissingDataException;
import com.example.demo.service.ProductService;


@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public ResponseEntity<ServiceStatus> addProduct (@RequestBody Product product) {
    	ServiceStatus serviceStatus=new ServiceStatus();
    	try {
    		if(product.getName() ==null || product.getName().isEmpty()) {
    			//serviceStatus.setMessage("name cannot be empty");
    			throw new Exception("name cannot be empty");
    		}
    	}
    	catch(Exception e) {
    		serviceStatus.setMessage(e.getMessage());
    	}
        return  new ResponseEntity<ServiceStatus>(serviceStatus,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addProducts")
    public  ResponseEntity<List<Product>> addProduct(@RequestBody List<Product> product) {
    	HttpHeaders header=new HttpHeaders();
    	header.add("descr", "creating multiple products" );
        return new ResponseEntity<List<Product>>(service.saveProducts(product),header,HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts() {
    	HttpHeaders header=new HttpHeaders();
    	header.add("descr", "get all the products" );
        return new ResponseEntity<List<Product>>(service.getProducts(), header,HttpStatus.OK);
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return new ResponseEntity<Product>( service.getProductById(id),HttpStatus.OK);
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name) {
        return  new ResponseEntity<Product>(service.getProductByName(name),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return  new ResponseEntity<Product>(service.updateProduct(product),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
	public  ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		return new ResponseEntity<String>(service.deleteProduct(id),HttpStatus.OK);
	}
    @GetMapping("/productbyStatus")
    public  List<Product> getUsersByApprovalStatusAndsearchKey(@RequestParam("searchKey") String searchKey, @RequestParam String approvalStatus) {
    	ServiceStatus serviceStatus = new ServiceStatus();
		try {
			List<Product> users = service.getUsersByapprovalStatus(searchKey, approvalStatus);
			if (users != null && !users.isEmpty()) {
				serviceStatus.setMessage("Successfully fetched data");
			} else {
			serviceStatus.setMessage("No data found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			serviceStatus.setMessage(e.getMessage());
		}
		
		return (List<Product>) serviceStatus;
}
    	
    	
    }
