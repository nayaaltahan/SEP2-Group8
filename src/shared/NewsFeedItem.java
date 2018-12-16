package shared;

import java.io.Serializable;

public interface NewsFeedItem extends Serializable {
	public MyDate getTime();
	public String getName();
	public String getText();
}
