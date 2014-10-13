#SirenJeeni: Java framework to output Siren hypermedia

__Current version: 0.3.1__

Please bear with me for a couple of week while I get this repo setup and aligned. 

You can contact me at [adam@Jeeni.co.uk](mailto:adam@jeeni.co.uk)

##Example

The following code uses a set of Java classes to construct the Siren JSON found at Kevin Swiber's [github Siren Site](https://github.com/kevinswiber/siren)

I'll give a fuller description in time, but it sould be easy to follow.

```Java
final Entity entity = new Entity(new String[] {"order"}, "http://api.x.io/orders/42");
		
		entity.addProperty("orderNumber", 42)
			.addProperty("itemCount", 3)
			.addProperty("status", "pending")
			
			.addSubEntity(new SubEntity(new String[] {"items", "collection"}, "http://api.x.io/orders/42/items", new String[] {"http://x.io/rels/order-items"}));
			
		final SubEntity customer = new SubEntity(new String[] {"info", "customer"}, new String[] {"http://x.io/rels/customer"});
		customer.addProperty("customerId", "pj123")
		.addProperty("name", "Peter Joseph")
		.add(new Link("http://api.x.io/customers/pj123"));
			
		entity.addSubEntity(customer);
		
		ActionLink action = new ActionLink("add-item", "Add Item", "http://api.x.io/orders/42/items", HttpMethod.POST);
		action.setFormType("application/x-www-form-urlencoded")
		.addDataField(new LinkDataField("orderNumber", InputTypes.HIDDEN, "42"))
		.addDataField(new LinkDataField("productCode", InputTypes.TEXT))
		.addDataField(new LinkDataField("quantity", InputTypes.NUMBER));
		
		entity.add(action);
		
		entity.add(new Link(new String[] {"previous"}, "http://api.x.io/orders/41"))
		.add(new Link(new String[] {"next"}, "http://api.x.io/orders/43"));
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(entity));
		} catch (Exception e) {
			e.printStackTrace();
		} 
```
#Output
The code above will print out the following JSON. Which is syntatically identical [on Kenin's site](https://github.com/kevinswiber/siren)


```json
{
    "class": [
        "order"
    ],
    "properties": {
        "orderNumber": 42,
        "status": "pending",
        "itemCount": 3
    },
    "entities": [
        {
            "class": [
                "items",
                "collection"
            ],
            "rel": [
                "http://x.io/rels/order-items"
            ],
            "href": "http://api.x.io/orders/42/items"
        },
        {
            "class": [
                "info",
                "customer"
            ],
            "rel": [
                "http://x.io/rels/customer"
            ],
            "properties": {
                "name": "Peter Joseph",
                "customerId": "pj123"
            },
            "links": [
                {
                    "rel": [
                        "self"
                    ],
                    "href": "http://api.x.io/customers/pj123"
                }
            ]
        }
    ],
    "actions": [
        {
            "name": "add-item",
            "title": "Add Item",
            "method": "POST",
            "href": "http://api.x.io/orders/42/items",
            "type": "application/x-www-form-urlencoded",
            "fields": [
                {
                    "name": "orderNumber",
                    "type": "HIDDEN",
                    "value": "42"
                },
                {
                    "name": "quantity",
                    "type": "NUMBER"
                },
                {
                    "name": "productCode",
                    "type": "TEXT"
                }
            ]
        }
    ],
    "links": [
        {
            "rel": [
                "self"
            ],
            "href": "http://api.x.io/orders/42"
        },
        {
            "rel": [
                "previous"
            ],
            "href": "http://api.x.io/orders/41"
        },
        {
            "rel": [
                "next"
            ],
            "href": "http://api.x.io/orders/43"
        }
    ]
}
```
