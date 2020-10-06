package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class JSONObjectTest {

    @Test
    public void practice_one() {
        JSONObject json = new JSONObject();
        JSONObject store = new JSONObject();
        JSONArray  array = new JSONArray();
        JSONObject  bookItem1 = new JSONObject();
        bookItem1.put("category", "reference");
        bookItem1.put("author", "Nigen Rees");
        bookItem1.put("title", "Sayings of the Century");
        bookItem1.put("price", 6.96);

        JSONObject  bookItem2 = new JSONObject();
        bookItem2.put("category", "fiction");
        bookItem2.put("author", "Evelyn Waugh");
        bookItem2.put("title", "Sword of Honour");
        bookItem2.put("price", 12.99);

        JSONObject  bookItem3 = new JSONObject();
        bookItem3.put("category", "fiction");
        bookItem3.put("author", "Herman Melville");
        bookItem3.put("title", "Moby Dick");
        bookItem3.put("isbn", "0-553-21311-3");
        bookItem3.put("price", 8.99);

        array.add(bookItem1);
        array.add(bookItem2);
        array.add(bookItem3);

        store.put("store", array);


        System.out.println(store.toString());
    }
}
