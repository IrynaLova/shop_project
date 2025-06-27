package client;

import app.controller.ProductController;

import app.domain.Product;
import app.repositoriees.ProductRepository;
import app.repositoriees.ProductRepositoryMap;
import app.service.ProductService;
import app.service.ProductServiceIml;

import java.util.Scanner;

public class Client {

    private static Scanner scanner;
    private static ProductController productController;

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryMap();

        ProductService productService = new ProductServiceIml(productRepository);

        productController = new ProductController(productService);

        scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Выберите действие: ");
                System.out.print("1. Операция с покупателями");
                System.out.print("2. Операция с покупателями");
                System.out.print("0. Выход");
                System.out.println("Ваш выбор");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productOperations();
                        break;
                    case 2:
                        customerOperations();
                        break;
                    case 0:
                        return;
                    default:
                            System.out.println("Некорректный ввод");
                            break;
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void productOperations() {
        while (true) {
            try{
                System.out.print("Выберите действие с продуктом: ");
                System.out.println("1. Сохранение продукта ");
                System.out.println("2. Получение всех продуктов");
                System.out.println("3. Получение одного продукта по ID");
                System.out.println("4. Изменение одного продукта");
                System.out.println("5. Удаление одного продукта по ID");
                System.out.println("6. Удаление одного продукта по наименованию");
                System.out.println("7. Восстановление одного продукта по ID");
                System.out.println("8. Получение количества продуктов");
                System.out.print("9. Получение общей стоимости продуктов");
                System.out.println("10. Получение средней стоимости продуктов");
                System.out.print("0. Выход");
                System.out.println("Ваш выбор");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Введите наименование продукта");
                        String name = scanner.nextLine();
                        System.out.print("Введите цену продукта ");
                        double price = Double.parseDouble(scanner.nextLine());

                        Product product = productController.save(name, price);
                        System.out.println("Сохранение продукт: ");
                        System.out.println(product);
                        break;
                    case 2:
                       productController.getAll().forEach(System.out::println);
                       break;
                    case 3:
                        System.out.println("Введите ID продукта: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        Product foundProduct = productController.getById(id);
                        System.out.println("Найденный продукт: ");
                        System.out.println(foundProduct);
                        break;
                    case 4:
                        System.out.println("Введите ID продукта: ");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите наименование продукта: ");
                        name = scanner.nextLine();
                        System.out.println("Введите цену продукта");
                        price = Double.parseDouble(scanner.nextLine());

                        productController.update(id, name, price);
                        break;
                    case 5:
                        System.out.println("Введите ID продукта: ");
                        id = Long.parseLong(scanner.nextLine());
                        productController.deleteById(id);
                        break;
                    case 6:
                        System.out.println("Введите наименование продукта: ");
                        name = scanner.nextLine();
                        productController.deleteByName(name);
                        break;
                    case 7:
                        System.out.println("Введите ID продукта: ");
                        id = Long.parseLong(scanner.nextLine());
                        productController.restoreById(id);
                        break;
                    case 8:
                        System.out.println("Общее количество продуктов: "
                                + productController.getActiveProductTotalCount());
                        break;
                    case 9:
                        System.out.println("Общая стоимость продуктов: "
                                + productController.getActiveProductTotalCost());
                        break;
                    case 10:
                        System.out.println("Средняя цена продуктов: "
                                + productController.getActivePrice());
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Некорректный ввод");
                        break;

                }

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    private static void customerOperations() {
        while (true) {
            try {
                System.out.print("<UNK> <UNK> <UNK> <UNK>: ");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
