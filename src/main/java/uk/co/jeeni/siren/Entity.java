package uk.co.jeeni.siren;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "class", "properties", "entities", "actions", "links" })
public class Entity implements ISirenEntity {

	@JsonProperty("class")
	private final String[] classes;
	private Properties properties = new Properties();
//	private ISirenEntity[] entities;
	private SubEntity[] entities;
	private ActionLink[] actions;
	private Link[] links;
	
	protected String domain;
	
	public Entity(String[] classes, String selfUrl){
		this.classes = classes;
		this.addLink(new Link(selfUrl));
		this.domain = null;
	}
	
	public Entity(String[] classes, String domain, String relativeSelfUrl){
		this.classes = classes;
		this.domain = domain;
		this.addLink(new Link(relativeSelfUrl));
	}
	
	protected Entity(String[] classes){
		this.classes = classes;
		this.domain = null;
	}
	
	public Entity addProperty(String name, Object value){
		if(name != null && value != null
				&& name.trim().length() > 0 && value.toString().trim().length() > 0){
			properties.put(name.trim(), value);
		}
		return this;
	}
	
//	public Entity addLinkToSubEntity(LinkToSubEntity entity){
//		if(entity != null){
//			final Set<ISirenEntity> links = addItem(entity, entities);
//			entities = links.toArray(new ISirenEntity[links.size()]);
//		}
//		return this;
//	}

	public Entity addSubEntity(SubEntity entity){
		if(entity != null){
			if(this.domain != null){
				entity.buildUrls(domain);
			}
			final Set<SubEntity> links = addItem(entity, entities);
			entities = links.toArray(new SubEntity[links.size()]);
		}
		return this;
	}

	public Entity addLink(Link link){
		if(link != null){
			final Set<Link> linkSet = addItem(link, links);
			links = linkSet.toArray(new Link[linkSet.size()]);
		}
		return this;
	}
	
	public Entity addAction(ActionLink link){
		if(link != null){
			final Set<ActionLink> links = addItem(link, actions);
			actions = links.toArray(new ActionLink[links.size()]);
		}
		return this;
	}
	
	private <T> Set<T> addItem(T item, T[] array){
		
		final Set<T> items = new HashSet<T>();
		
		if(array != null && array.length > 0){
			Collections.addAll(items, array);
		}
		items.add(item);
		return items;
	}

	public final Link[] getLinks() {
		return links;
	}

	public final String[] getClasses() {
		return classes;
	}

	public final Properties getProperties() {
		return properties;
	}

	public final ISirenEntity[] getEntities() {
		return entities;
	}

	public final ActionLink[] getActions() {
		return actions;
	}
	
	public final void buildUrls(){
		buildSubEntityUrls(domain);
		buildLinkUrls(domain);
		buildActionUrls(domain);
	}
	
	final void buildSubEntityUrls(String domain){
		if(entities != null){
			for(SubEntity subEntity : entities){
				subEntity.buildUrls(domain);
			}
		}
	}
	final void buildLinkUrls(String domain){
		if(links != null && links.length > 0){
			for(Link link : links){
				link.buildUrl(domain);
			}
		}
	}
	
	final void buildActionUrls(String domain){
		if(actions != null && actions.length > 0){
			for(ActionLink actionLink : actions){
				actionLink.buildUrls(domain);
			}
		}
	}

}
