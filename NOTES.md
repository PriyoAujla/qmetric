# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

1. Currently a Product has a structural identity and this is fine if we don't care so much about 
the identity of the product. However discounting rules may only be applicable to the identity of the 
product (Baked beans, Vegetable, etc). 
Two products with the same pricePerUnit or pricePerKg will currently be seen as being the same.

2. Added Guava to make it easier to create collections like Set etc.