package ir.unicodes.doctor.classes;

public class Variables {

    public static final String Tag              = "MYAPP";

    public static final String TOKEN            = "Z4_ru@9j";

    public static final String catId            = "catId";
    public static final String Id               = "Id";
    public static final String catIndex         = "catIndex";
    public static final String Index            = "Index";

    public static final String getNews          = "getNews";
    public static final String getMagazine      = "getMagazine";
    public static final String getServices      = "getServices";
    public static final String getInsurance     = "getInsurance";
    public static final String getParts         = "getParts";
    public static final String getCareBefore    = "getCareBefore";
    public static final String getCareAfter     = "getCareAfter";
    public static final String getDrugs         = "getDrugs";
    public static final String getFavorites     = "getFavorites";
    public static final String getVideos        = "getVideos";
    public static final String getImages        = "getImages";

}// end class

/**
 * Both below two methods do the same thing :

     1 :
         News : (Objects)
         URL +/+ GetItem
         {catId:NEWS_ID,Token=TOKEN}

         News : (Folder)
         URL +/+ GetCategory
         {Id:NEWS_ID,Token=TOKEN}

     _______________________________

     2 :
         News : (Objects)
         URL +/+ GetItem
         {catIndex:NEWS_INDEX,Token=TOKEN}

         News : (Folder)
         URL +/+ GetCategory
         {Index:NEWS_INDEX,Token=TOKEN}


 ************************************
 * this method get all objects of folders and sub folder in the root folder with id or index mentioned

     News : (Objects and Folders)
     URL +/+ GetFullItems
     {Id:NEWS_ID / NEWS_INDEX,Token=TOKEN}


 ************************************
 * this method get all categories inside folder and sub folders of the root folder with id or index mentioned
     News : (Categories inside folder)
     URL +/+ GetFullCategories
     {Id:NEWS_ID / NEWS_INDEX,Token=TOKEN}


**/
