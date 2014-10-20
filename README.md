#SirenJeeni: Java framework to output Siren hypermedia

__Current version: 0.5.1__

## What's the Point of Siren-Jeeni
Siren-Jeeni is an enabler that will allow companies who build systems in Java to expose them as Siren Hypermedia APIs. This means companies like Screwfix, Tesco and others can expose their core functionality as API services and open these up to the world. A bit like Google, eBay and Amazon have done.

With this, developers will be able to use those APIs to create new apps for custom markets or new technologies such as Google Glass.

Imagine wearing Google Glasses and looking at products on the shelf at your local supermarket and seeing the price of the same product at all your other favourite shopping places. Or integrating Screwfix's ordering API into your company's purchasing software.

This is what Siren-Jeeni will enable you to do.A good question that many have asked (Mark Rees), and deserves an answer...

Siren-Jeeni is an enabler that will allow companies who build systems in Java to expose them as Siren Hypermedia APIs. This means companies like Screwfix, Tesco and others can expose their core functionality as API services and open these up to the world. A bit like Google, eBay and Amazon have done.

With this, developers will be able to use those APIs to create new apps for custom markets or new technologies such as Google Glass.

Imagine wearing Google Glasses and looking at products on the shelf at your local supermarket and seeing the price of the same product at all your other favourite shopping places. Or integrating Screwfix's ordering API into your company's purchasing software.

This is what Siren-Jeeni will enable you to do.


You can contact me at [adam@jeeni.co.uk](mailto:adam@jeeni.co.uk?Subject=SirenJeeni)

## SirenJeeni Example

The following Java code uses a set of classes to construct the Siren JSON found at [Kevin Swiber's github Siren Site](https://github.com/kevinswiber/siren).

Note that on the first line:
```Java
  final Entity entity = new Entity(new String[] {"order"}, "http://api.x.io/", "/orders/42");
```
The second parameter `"http://api.x.io/"`, sets the domain that will be used with all subsequent relative links. This helps avoid mistakes and repetition. 

However, if you use a full URL structure in a link then that will be used as entered. In other words this allows you to reference links in different domains. 

Also note the final line before generating the JSON:
```Java
  entity.buildUrls();
```
This line is absolutely required as it builds all the URL object internally and thus ensures they are correctly formatted etc.

I've also tried to use the Java Fluent style of coding where possible.

```Java
// Create the parent 'Order' entity. 
//Note the domain "http://api.x.io/" is uses so all other URL references can be relative.
final Entity entity = new Entity(new String[] {"order"}, "http://api.x.io/", "/orders/42");

// Create parent entity properties
entity.addProperty("orderNumber", 42)
  .addProperty("itemCount", 3)
  .addProperty("status", "pending")
  // Add Sub entity for Collection
  .addSubEntity(new SubEntity(new String[] {"items", "collection"}, "/orders/42/items", new String[] {"/rels/order-items"}));
  
// Add the 'next' and 'previous' links
entity.addLink(new Link(new String[] {"previous"}, "/orders/41"))
.addLink(new Link(new String[] {"next"}, "/orders/43"));


// THIS ENTITY DOES NOT KNOW ABOUT THE PARENT DOMAIN
/* The CustomerInfo Entity */
SubEntity customerInfo = new SubEntity(new String[] {"info", "customer"}, new String[] {"rels/customer"});
  // Add Customer properties
customerInfo.addProperty("customerId", "pj123")
  .addProperty("name", "Peter Joseph")
  // Add 'self' link for Customer
  .addLink(new Link("customers/pj123"));

// Add Sub entity for Customer
entity.addSubEntity(customerInfo);

// Create action link entity and fields
ActionLink actionLink = new ActionLink("add-item", "Add Item", "orders/42/items", HttpMethod.POST);
actionLink.setFormType("application/x-www-form-urlencoded")
.addDataField(new DataField("orderNumber", InputTypes.HIDDEN, "42"))
.addDataField(new DataField("productCode", InputTypes.TEXT))
.addDataField(new DataField("quantity", InputTypes.NUMBER));

// Add action to parent entity.
entity.addAction(actionLink);

// Build and check all links
entity.buildUrls();

// Output the JSON String
ObjectMapper mapper = new ObjectMapper();
try {
  System.out.println(mapper.writeValueAsString(entity));
} catch (Exception e) {
  e.printStackTrace();
} 
```
## Siren JSON Output
The code above will print out the following JSON. Which is syntatically identical to that [on Kevin's site](https://github.com/kevinswiber/siren)


```json
{
    "class": ["order"],
    "properties": {
        "orderNumber": 42,
        "status": "pending",
        "itemCount": 3
    },
    "entities": [
        {
            "class": ["items", "collection"],
            "rel": ["http://api.x.io/rels/order-items"],
            "href": "http://api.x.io/orders/42/items"
        },
        {
            "class": ["info", "customer"],
            "rel": ["http://api.x.io/rels/customer"],
            "properties": {
                "name": "Peter Joseph",
                "customerId": "pj123"
            },
            "links": [
                {"rel": ["self"],"href": "http://api.x.io/customers/pj123"}
            ]
        }
    ],
    "actions": [
        {
            "name": "add-item",
            "title": "Add Item",
            "href": "http://api.x.io/orders/42/items",
            "method": "POST",
            "type": "application/x-www-form-urlencoded",
            "fields": [
                { "name": "productCode", "type": "TEXT" },
                { "name": "orderNumber", "type": "HIDDEN", "value": "42"},
                { "name": "quantity", "type": "NUMBER" }
            ]
        }
    ],
    "links": [
        { "rel": ["previous"], "href": "http://api.x.io/orders/41" },
        { "rel": ["self"], "href": "http://api.x.io/orders/42"},
        { "rel": ["next" ], "href": "http://api.x.io/orders/43"}
    ]
}
```
