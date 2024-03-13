package pageObjects.saurceLab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saurceLab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPO extends BasePage {
    WebDriver driver;

    public ProductPO(WebDriver driver) {
        this.driver = driver;
    }

    public void selectItemInProductSortDropdown(String value) {
        waitForElementClickable(driver, ProductPageUI.SORT_ITEM_DROPDOWN);
        selectItemInDefaultDropDown(driver, ProductPageUI.SORT_ITEM_DROPDOWN, value);
    }

    public boolean isProductNameSortByAscending() {
        //Khai báo 1 ArrayList để chứa các product name
        ArrayList<String> productUIList = new ArrayList<String>();

        //Lấy ra hết tất cả các text product name
        List<WebElement> productNameText = getElements(driver, ProductPageUI.PRODUCT_NAME);

        //Dùng vòng lặp để getText và add vào ArrayList trên
        for (WebElement productName : productNameText) {
            productUIList.add(productName.getText());
        }

        //Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem có đúng hay ko
        ArrayList<String> productSortList = new ArrayList<String>();
        productSortList.addAll(productUIList);

        //Sort productSortList
        Collections.sort(productSortList);

        //so sánh 2 list có bằng nhau hay không
        return productSortList.equals(productUIList);

    }

    public boolean isProductNameSortByDescending() {
        //Khai báo 1 ArrayList để chứa các product name
        ArrayList<String> productUIList = new ArrayList<String>();

        //Lấy ra hết tất cả các text product name
        List<WebElement> productNameText = getElements(driver, ProductPageUI.PRODUCT_NAME);

        //Dùng vòng lặp để getText và add vào ArrayList trên
        for (WebElement productName : productNameText) {
            productUIList.add(productName.getText());
        }

        //Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem có đúng hay ko
        ArrayList<String> productSortList = new ArrayList<String>();
        productSortList.addAll(productUIList);

        //Sort productSortList
        Collections.sort(productSortList);

        //Reverse productSortList
        Collections.reverse(productSortList);

        //so sánh 2 list có bằng nhau hay không
        return productSortList.equals(productUIList);
    }

    public boolean isProductPriceSortByAscending() {
        //Khai báo 1 ArrayList để chứa các product name
        ArrayList<Float> productUIList = new ArrayList<Float>();

        //Lấy ra hết tất cả các text product name
        List<WebElement> productPriceText = getElements(driver, ProductPageUI.PRODUCT_PRICE);

        //Dùng vòng lặp để getText và add vào ArrayList trên
        for (WebElement productPrice : productPriceText) {
            productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
        }

        //Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem có đúng hay ko
        ArrayList<Float> productSortList = new ArrayList<Float>();
        productSortList.addAll(productUIList);

        //Sort productSortList
        Collections.sort(productSortList);

        //so sánh 2 list có bằng nhau hay không
        return productSortList.equals(productUIList);
    }

    public boolean isProductPriceSortByDescending() {
        //Khai báo 1 ArrayList để chứa các product name
        ArrayList<Float> productUIList = new ArrayList<Float>();

        //Lấy ra hết tất cả các text product name
        List<WebElement> productNameText = getElements(driver, ProductPageUI.PRODUCT_PRICE);

        //Dùng vòng lặp để getText và add vào ArrayList trên
        for (WebElement productName : productNameText) {
            productUIList.add(Float.parseFloat(productName.getText().replace("$", "")));
        }

        //Tạo ra 1 ArrayList mới để sort dữ liệu trong ArrayList cũ xem có đúng hay ko
        ArrayList<Float> productSortList = new ArrayList<Float>();
        productSortList.addAll(productUIList);

        //Sort productSortList
        Collections.sort(productSortList);

        //Reverse productSortList
        Collections.reverse(productSortList);

        //so sánh 2 list có bằng nhau hay không
        return productSortList.equals(productUIList);
    }
}
