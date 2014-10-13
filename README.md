#SirenJeeni: Java framework to output Siren hypermedia



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
