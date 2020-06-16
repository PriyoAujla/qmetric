# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

1. Currently a Product has a structural identity and this is fine if we don't care so much about 
the identity of the product. However discounting rules may only be applicable to the identity of the 
product (Baked beans, Vegetable, etc). 
Two products with the same pricePerUnit or pricePerKg will currently be seen as being the same.

2. Added Guava to make it easier to create collections like Set etc.

3. Added apache commons lang to gain access to the equals and hashcode builder to create data classes.

4. I changed the Item interface so it can return the ProductId, allowing the discount logic
to identify an item not by reference or it's pricePerUnit/priceByWeight but by an id value.
However I couldn't get the equals/hashcode to work properly in my test and ran out of time to debug why.

5. Basket of items is passed to a list of DiscountSchemes that will apply the discounts, making it
extensible to new discounts.

6. Each DiscountScheme takes the entire basket of items, this is currently the easiest thing to do but
it also has the added benefit of allowing a discount to see the whole basket of items when deciding
how to apply a discount.

7. I want each discount scheme to return not just the discount but an identifier, otherwise discounts are just
a list of subtractions without much context. 
Premature design decision at the moment but done mainly for operational reasons (ie debugging discount issues).

8. Currently my assumption is that an item will only fall into one discount scheme at a time, this 
however is currently not enforced anywhere.

9. If I had more time I would like to raise the level of abstraction so that the code is dealing with
different primitives for example Money rather can BigDecimal, I think the rounding up could be encapsulated in Money.

10. BuyOneGetOneFree can probably be generalised to XForY.