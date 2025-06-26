package app.service;

import app.domain.Product;
import app.exceptions.ProductNotFoundException;
import app.exceptions.ProductSaveException;
import app.exceptions.ProductUpdateException;
import app.repositoriees.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceIml implements ProductService {

    private final ProductRepository repository;

   public ProductServiceIml(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        if(product == null){
            throw new ProductSaveException("Product cannot be null");
        }

        String Name = product.getName();
        if(Name == null || Name.trim().isEmpty() || Name.length() < 3){
            throw new ProductSaveException("Product name should be at least 3 characters long");
        }

        if(product.getPrice() <= 0){
            throw new ProductSaveException("Price price cannot be negative and zero");
        }

        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return repository.findAll().stream()
                .filter(x -> x.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public Product getById(Long id) {
        Product product = repository.findById(id);

        if(product == null || !product.isActive()){
            throw new ProductNotFoundException("Product with id = " + " nor found");
        }
        return product;
    }

    @Override
    public void update(Product product) {
        if(product == null){
            throw new ProductUpdateException("Product cannot be null");
        }

        Long id = product.getId();
        if(id == null || id < 0){
            throw new ProductUpdateException("Product id should be positive");
        }

        String productName = product.getName();
        if(productName == null || productName.trim().isEmpty() || productName.length() < 3){
            throw new ProductSaveException("Product name should be at least 3 characters long");
        }

        if(product.getPrice() <= 0){
            throw new ProductSaveException("Price price cannot be negative and zero");
        }

        repository.updateById(product);
    }

    @Override
    public void deleteById(Long id) {
        getById(id).setActive(false);
    }

    @Override
    public void deleteByName(String name) {
        Product product = getAllActiveProducts()
                .stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);

        if(product == null){
            throw new ProductSaveException("Product with name = " + name + " not found");
        }
        product.setActive(false);
    }

    @Override
    public void restoreById(Long id) {
        getById(id).setActive(true);
    }

    @Override
    public long getActiveProductTotalCount() {
        return getAllActiveProducts().size();
    }

    @Override
    public double getActiveProductTotalCost() {
        return getAllActiveProducts().stream()
                .mapToDouble(p -> p.getPrice())
                .sum();
    }

    @Override
    public double getActivePrice() {
        return getAllActiveProducts().stream()
                .mapToDouble(p -> p.getPrice())
                .average()
                .orElse(0);
   }

}