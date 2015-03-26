package shared.definitions;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public enum HexType
{
	@SerializedName("wood")
	WOOD,

	@SerializedName("brick")
	BRICK,

	@SerializedName("sheep")
	SHEEP,

	@SerializedName("wheat")
	WHEAT,

	@SerializedName("ore")
	ORE,

	@SerializedName("desert")
	DESERT,

	@SerializedName("water")
	WATER;
	
	public String toString()
	{
		switch(this)
		{
		case WOOD:
			return "wood";
		case BRICK:
			return "brick";
		case SHEEP:
			return "sheep";
		case WHEAT:
			return "wheat";
		case ORE:
			return "ore";
		case DESERT:
			return "desert";
		case WATER:
			return "water";
		default:
			return null;
		}
	}
	
//	public class HexTypeAdapter extends TypeAdapter<HexType> {
//
//		@Override
//		public void write(JsonWriter out, HexType value) throws IOException {
//			if (value == null) {
//				out.nullValue();
//			} else {
//				out.value(value.toString());
//			}
//		}
//		
//		@Override
//		public HexType read(JsonReader arg0) throws IOException {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//	}
}

