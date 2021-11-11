package ru.netology.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class ProductRepositoryTest {
    private ProductRepository repository = Mockito.mock(ProductRepository.class);
    private ProductManager manager = new ProductManager(repository);

    private Book first = new Book(1, "Алиса в Зазеркалье", 465, "Льюис Кэрролл");
    private Smartphone second = new Smartphone(2, "Redmi Note 8 Pro", 20900, "Xiaomi");
    private Book third = new Book(3, "Большая кулинарная книга", 1450, "Вильям Васильевич Похлёбкин");
    private Smartphone fourth = new Smartphone(4, "iPhone 12 mini", 54990, "Apple");
    private Smartphone fifth = new Smartphone(5, "POCO F2 Pro", 47900, "Xiaomi");
    private Product sixth = new Product(6, "Книга", 100);


    @BeforeEach
    public void productManager() {
        Product[] products = {first, second, third, fourth, fifth, sixth};
        doReturn(products).when(repository).findAll();
    }

    @AfterEach
    public void verifyRepository() {
        verify(repository).findAll();
    }

    @Test
    public void searchByManufacturer() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[]{fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoResultsByManufacturerName() {
        Product[] actual = manager.searchBy("Xiaomi");
        Product[] expected = new Product[]{second, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneResultByPhoneName() {
        Product[] actual = manager.searchBy("POCO");
        Product[] expected = new Product[]{fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneResultByBookName() {
        Product[] actual = manager.searchBy("Алиса");
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneResultByBookAuthor() {
        Product[] actual = manager.searchBy("Кэрролл");
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }


    @Test
    public void queryWithNoResult() {
        Product[] actual = manager.searchBy("Му-му");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

}