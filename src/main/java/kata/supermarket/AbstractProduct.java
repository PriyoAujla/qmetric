package kata.supermarket;

public abstract class AbstractProduct {
    ProductId productId;

    public AbstractProduct(ProductId productId) {
        this.productId = productId;
    }

    public ProductId getProductId() {
        return productId;
    }

}
