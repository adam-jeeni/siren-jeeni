package uk.co.jeeni.siren;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class SwiberTest {
	/**
	 * Tests that the same structured JSON as found at Kevin Swiber's GitHub project can be generated by
	 * using the SirenJeeni classes.
	 * 
	 * @see https://github.com/kevinswiber/siren
	 */
	@Test
	public void testCreatkevinswiberExample(){
		
		// Create the parent 'Order' entity
		final Entity entity = new Entity(new String[] {"order"}, "http://api.x.io/orders/42");
		
		// Create parent entity properties
		entity.addProperty("orderNumber", 42)
			.addProperty("itemCount", 3)
			.addProperty("status", "pending")
			
			// Add Sub entity for Collection
			.addSubEntity(new SubEntity(new String[] {"items", "collection"}, "http://api.x.io/orders/42/items", new String[] {"http://x.io/rels/order-items"}))
			
			// Add Sub entity for Customer
			.addSubEntity(new SubEntity(new String[] {"info", "customer"}, new String[] {"http://x.io/rels/customer"})
				
				// Add Customer properties
				.addProperty("customerId", "pj123")
				.addProperty("name", "Peter Joseph")
				
				// Add 'self' link for Customer
				.add(new Link("http://api.x.io/customers/pj123")));
			
		// Create action link entity and fields
		ActionLink action = new ActionLink("add-item", "Add Item", "http://api.x.io/orders/42/items", HttpMethod.POST);
		action.setFormType("application/x-www-form-urlencoded")
		.addDataField(new LinkDataField("orderNumber", InputTypes.HIDDEN, "42"))
		.addDataField(new LinkDataField("productCode", InputTypes.TEXT))
		.addDataField(new LinkDataField("quantity", InputTypes.NUMBER));
		
		// Add action to parent entity.
		entity.add(action);
		
		// Add the 'next' and 'previous' links
		entity.add(new Link(new String[] {"previous"}, "http://api.x.io/orders/41"))
		.add(new Link(new String[] {"next"}, "http://api.x.io/orders/43"));
		
		
		// Output the JSON String
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(entity));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
