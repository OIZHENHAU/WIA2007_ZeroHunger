package com.example.wia2007_zerohunger.Part4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizDatabase";
    private static final int DATABASE_VERSION = 7;

    public static final String TABLE_NAME = "Questions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_OPTION_A = "optionA";
    public static final String COLUMN_OPTION_B = "optionB";
    public static final String COLUMN_OPTION_C = "optionC";
    public static final String COLUMN_OPTION_D = "optionD";
    public static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    public static final String COLUMN_DIFFICULTY = "difficulty";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CONTENT + " TEXT, " +
                COLUMN_OPTION_A + " TEXT, " +
                COLUMN_OPTION_B + " TEXT, " +
                COLUMN_OPTION_C + " TEXT, " +
                COLUMN_OPTION_D + " TEXT, " +
                COLUMN_CORRECT_ANSWER + " TEXT, " +
                COLUMN_DIFFICULTY + " TEXT)";
        db.execSQL(CREATE_TABLE);
        String CREATE_PROGRESS_TABLE = "CREATE TABLE Progress (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "difficulty TEXT, " +
                "questions_completed INTEGER DEFAULT 0, " +
                "correct_answers INTEGER DEFAULT 0)";
        db.execSQL(CREATE_PROGRESS_TABLE);

        db.execSQL("CREATE TABLE Experts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "specialization TEXT NOT NULL, " +
                "rating REAL NOT NULL, " +
                "avatar TEXT, " +
                "bio TEXT)");

        db.execSQL("CREATE TABLE Appointments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "expert_id INTEGER NOT NULL, " +
                "date TEXT NOT NULL, " +
                "time_slot TEXT NOT NULL, " +
                "status TEXT DEFAULT 'Scheduled', " +
                "FOREIGN KEY (expert_id) REFERENCES Experts(id))");

        db.execSQL("CREATE TABLE Consultations (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "expert_id INTEGER NOT NULL, " +
                "date TEXT NOT NULL, " +
                "summary TEXT NOT NULL, " +
                "status TEXT NOT NULL, " +
                "FOREIGN KEY (expert_id) REFERENCES Experts(id))");
        String CREATE_COURSES_TABLE = "CREATE TABLE Courses (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, " +
                "description TEXT, " +
                "url TEXT, " +
                "difficulty TEXT)";
        db.execSQL(CREATE_COURSES_TABLE);

        insertInitialExperts(db);
        insertInitialCourses(db);
        insertInitialExperts(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS Progress");
        db.execSQL("DROP TABLE IF EXISTS Experts");
        db.execSQL("DROP TABLE IF EXISTS Appointments");
        db.execSQL("DROP TABLE IF EXISTS Consultations");
        db.execSQL("DROP TABLE IF EXISTS COURSES");
        onCreate(db); // 重新创建所有表
    }


    // 插入问题方法
    public void insertQuestion(String content, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String difficulty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_OPTION_A, optionA);
        values.put(COLUMN_OPTION_B, optionB);
        values.put(COLUMN_OPTION_C, optionC);
        values.put(COLUMN_OPTION_D, optionD);
        values.put(COLUMN_CORRECT_ANSWER, correctAnswer);
        values.put(COLUMN_DIFFICULTY, difficulty);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // 获取问题方法
    public Cursor getQuestionsByDifficulty(String difficulty) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE difficulty=?", new String[]{difficulty});
    }
    //在这里防止题目被重复插入
    public boolean isDatabaseInitialized() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0; // 如果题目已存在，返回 true
    }
    public void insertAllQuestions() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // 示例：插入 Easy 难度的题目
        String[][] easyQuestions = {
                {"What is sustainable farming?", "Using less water and resources", "Producing more with harmful chemicals", "Focusing on maximum profit", "Ignoring the environment", "Using less water and resources", "Easy"},
                {"Which nutrient is most important for plant growth?", "Calcium", "Nitrogen", "Iron", "Magnesium", "Nitrogen", "Easy"},
                {"What is food security?", "Having enough food to meet dietary needs", "Growing more food than needed", "Reducing food wastage", "Exporting food", "Having enough food to meet dietary needs", "Easy"},
                {"What is the main cause of hunger globally?","Poverty","Overpopulation" ,"Lack of water" ,"Too much food" ,"Poverty","Easy"},
                {"What is the main cause of hunger globally?", "Poverty", "Overpopulation", "Lack of water", "Too much food", "Poverty", "Easy"},
                {"What is irrigation?", "Watering crops artificially", "Using only rainwater", "Stopping water flow", "Building dams", "Watering crops artificially", "Easy"},
                {"What is compost?", "Decomposed organic material", "Chemical fertilizer", "A type of crop", "A pest", "Decomposed organic material", "Easy"},
                {"Which farming method avoids synthetic pesticides?", "Organic farming", "Industrial farming", "Hydroponics", "Traditional farming", "Organic farming", "Easy"},
                {"What is crop diversity?", "Growing different types of crops", "Growing only one type of crop", "Using only one fertilizer", "Reducing crop production", "Growing different types of crops", "Easy"},
                {"What does Zero Hunger aim to achieve?", "Reducing hunger and malnutrition", "Promoting urbanization", "Exporting food to other countries", "Increasing food prices", "Reducing hunger and malnutrition", "Easy"},
                {"What is food waste?", "Edible food discarded", "Non-edible food", "Leftover water", "Fertilized soil", "Edible food discarded", "Easy"},
                {"What is sustainable agriculture?", "Practices that maintain resources for future generations", "Maximizing short-term yields", "Using excessive fertilizers", "Deforesting land for farming", "Practices that maintain resources for future generations", "Easy"},
                {"What is hunger caused by disasters called?", "Emergency hunger", "Chronic hunger", "Acute hunger", "Malnutrition", "Acute hunger", "Easy"},
                {"Which nutrient do legumes help replenish in the soil?", "Nitrogen", "Potassium", "Phosphorus", "Calcium", "Nitrogen", "Easy"},
                {"What is the role of bees in agriculture?", "Pollination", "Fertilizer production", "Watering crops", "Weeding", "Pollination", "Easy"},
                {"What is the main source of energy in a human diet?", "Carbohydrates", "Proteins", "Fats", "Vitamins", "Carbohydrates", "Easy"},
                {"What is a key feature of organic farming?", "Avoiding synthetic chemicals", "Maximizing profits", "Using only genetically modified seeds", "Ignoring soil health", "Avoiding synthetic chemicals", "Easy"},
                {"What does malnutrition include?", "Overnutrition and undernutrition", "Only undernutrition", "Only overnutrition", "Balanced diet", "Overnutrition and undernutrition", "Easy"},
                {"What is the main cause of soil erosion?", "Deforestation", "Crop rotation", "Composting", "Irrigation", "Deforestation", "Easy"},
                {"What does FAO stand for?", "Food and Agriculture Organization", "Federal Agricultural Office", "Food and Animal Office", "Farming Assistance Organization", "Food and Agriculture Organization", "Easy"}
                //可以 继续添加其他 Easy 难度的题目，方便以后拓展
        };

        // 遍历插入
        for (String[] question : easyQuestions) {
            values.put(COLUMN_CONTENT, question[0]);
            values.put(COLUMN_OPTION_A, question[1]);
            values.put(COLUMN_OPTION_B, question[2]);
            values.put(COLUMN_OPTION_C, question[3]);
            values.put(COLUMN_OPTION_D, question[4]);
            values.put(COLUMN_CORRECT_ANSWER, question[5]);
            values.put(COLUMN_DIFFICULTY, question[6]);

            db.insert(TABLE_NAME, null, values);
        }

        // 示例：插入 Medium 难度的题目
        String[][] mediumQuestions = {
                {"What is crop rotation?", "Planting the same crop every year", "Alternating crops to improve soil health", "Growing crops on hills", "Using chemical fertilizers", "Alternating crops to improve soil health", "Medium"},
                {"Which UN agency focuses on agriculture and hunger?", "UNICEF", "WHO", "FAO", "UNESCO", "FAO", "Medium"},
                {"What is the purpose of food banks?", "Distributing surplus food to those in need", "Selling food at high prices", "Storing food for export", "Producing food", "Distributing surplus food to those in need", "Medium"},
                {"What is a greenhouse?", "A structure to grow plants in controlled conditions", "A house painted green", "A place to store crops", "A type of farming tool", "A structure to grow plants in controlled conditions", "Medium"},
                {"What does 'food sovereignty' mean?", "Local control over food systems", "Exporting surplus food", "Centralized food policies", "Producing only staple crops", "Local control over food systems", "Medium"},
                {"Which farming method uses nutrient-rich water to grow plants?", "Hydroponics", "Crop rotation", "Organic farming", "Terrace farming", "Hydroponics", "Medium"},
                {"What is the main goal of precision agriculture?", "Maximizing crop yield while minimizing resource use", "Growing only organic crops", "Eliminating farm labor", "Increasing fertilizer use", "Maximizing crop yield while minimizing resource use", "Medium"},
                {"What is a staple crop in sub-Saharan Africa?", "Maize", "Barley", "Soybeans", "Rice", "Maize", "Medium"},
                {"What is the impact of overgrazing?", "Soil erosion and degradation", "Improved soil fertility", "Increased biodiversity", "Better crop production", "Soil erosion and degradation", "Medium"},
                {"What is an example of a micronutrient?", "Iron", "Carbohydrates", "Fats", "Proteins", "Iron", "Medium"},
                {"What is slash-and-burn agriculture?", "Clearing land by cutting and burning vegetation", "Growing crops in dry areas", "Using chemical pesticides", "Planting crops without soil", "Clearing land by cutting and burning vegetation", "Medium"},
                {"What is the benefit of terrace farming?", "Prevents soil erosion on slopes", "Increases water usage", "Maximizes flat land usage", "Grows crops faster", "Prevents soil erosion on slopes", "Medium"},
                {"What is food insecurity?", "Lack of access to sufficient, safe, and nutritious food", "Having access to surplus food", "Wasting excess food", "Producing food without storage", "Lack of access to sufficient, safe, and nutritious food", "Medium"},
                {"What is monocropping?", "Growing the same crop repeatedly on the same land", "Growing multiple crops on one field", "Alternating crops yearly", "Using no fertilizers", "Growing the same crop repeatedly on the same land", "Medium"},
                {"What is the main advantage of drip irrigation?", "Minimizes water waste", "Requires no maintenance", "Increases water flow", "Floods the field evenly", "Minimizes water waste", "Medium"},
                {"Which soil component is vital for water retention?", "Clay", "Sand", "Gravel", "Limestone", "Clay", "Medium"},
                {"What is a benefit of cover crops?", "Prevent soil erosion and improve fertility", "Reduce biodiversity", "Increase soil salinity", "Block sunlight from crops", "Prevent soil erosion and improve fertility", "Medium"},
                {"What is the function of a windbreak on farms?", "Reduce wind speed and protect crops", "Increase wind flow for pollination", "Direct water flow", "Shade crops completely", "Reduce wind speed and protect crops", "Medium"},
                {"What does sustainable livestock farming aim to do?", "Reduce environmental impact of raising animals", "Increase animal weight rapidly", "Use only genetically modified feed", "Grow crops for export", "Reduce environmental impact of raising animals", "Medium"},
                {"What is the role of wetlands in agriculture?", "Improve water quality and act as natural filters", "Drain excess water for crops", "Block pests from crops", "Grow only aquatic plants", "Improve water quality and act as natural filters", "Medium"}
                // 继续添加其他 Medium 难度的题目
        };

        for (String[] question : mediumQuestions) {
            values.put(COLUMN_CONTENT, question[0]);
            values.put(COLUMN_OPTION_A, question[1]);
            values.put(COLUMN_OPTION_B, question[2]);
            values.put(COLUMN_OPTION_C, question[3]);
            values.put(COLUMN_OPTION_D, question[4]);
            values.put(COLUMN_CORRECT_ANSWER, question[5]);
            values.put(COLUMN_DIFFICULTY, question[6]);

            db.insert(TABLE_NAME, null, values);
        }

        // 示例：插入 Hard 难度的题目
        String[][] hardQuestions = {
                {"What is agroforestry?", "Farming without water", "Growing crops under trees for better productivity", "Planting only trees in a field", "Using chemical pesticides", "Growing crops under trees for better productivity", "Hard"},
                {"What is the significance of SDG 2?", "To reduce global warming", "To achieve Zero Hunger and ensure food security", "To promote industrial development", "To end poverty", "To achieve Zero Hunger and ensure food security", "Hard"},
                {"What is the Green Revolution?", "A movement to promote renewable energy", "A large increase in crop production due to new technologies", "Reducing carbon emissions in farming", "Using traditional farming methods", "A large increase in crop production due to new technologies", "Hard"},
                {"What is vertical farming?", "Growing crops in stacked layers", "Growing crops on mountains", "Farming underwater", "Using drones for farming", "Growing crops in stacked layers", "Hard"},
                {"What is nitrogen fixation?", "Converting nitrogen into usable forms for plants", "Increasing soil erosion", "Reducing water usage", "Removing pests", "Converting nitrogen into usable forms for plants", "Hard"},
                {"What is permaculture?", "Designing agricultural systems that mimic natural ecosystems", "Using only chemicals for high yields", "Maximizing short-term profits", "Clearing forests for farming", "Designing agricultural systems that mimic natural ecosystems", "Hard"},
                {"What is aquaponics?", "Combining fish farming with hydroponics", "Growing plants underwater", "Using aquifers for irrigation", "Planting crops in waterlogged soil", "Combining fish farming with hydroponics", "Hard"},
                {"What is a food desert?", "An area with limited access to affordable and nutritious food", "A region with no agriculture", "A place with high food production", "An area with excessive food waste", "An area with limited access to affordable and nutritious food", "Hard"},
                {"What is regenerative agriculture?", "Farming practices that restore soil and ecosystem health", "Using maximum fertilizers for high yield", "Planting crops without rotation", "Building more irrigation systems", "Farming practices that restore soil and ecosystem health", "Hard"},
                {"What is biochar?", "A carbon-rich material used to improve soil fertility", "A pesticide", "A synthetic fertilizer", "A genetically modified crop", "A carbon-rich material used to improve soil fertility", "Hard"},
                {"What is a major challenge for smallholder farmers?", "Limited access to markets and technology", "Excess land for farming", "Overproduction of crops", "Lack of water in urban areas", "Limited access to markets and technology", "Hard"},
                {"What is intercropping?", "Planting two or more crops together for mutual benefit", "Growing the same crop continuously", "Using pesticides between crop cycles", "Separating crops in large fields", "Planting two or more crops together for mutual benefit", "Hard"},
                {"What is soil salinization?", "Accumulation of salts in soil, reducing fertility", "Adding organic matter to soil", "Using less water for irrigation", "Rotating crops to maintain soil health", "Accumulation of salts in soil, reducing fertility", "Hard"},
                {"What is food resilience?", "The ability to ensure food availability during crises", "Producing maximum food at all times", "Exporting surplus food", "Increasing food prices during scarcity", "The ability to ensure food availability during crises", "Hard"},
                {"What is polyculture farming?", "Growing multiple types of crops in the same space", "Growing only one type of crop over large areas", "Using only chemical fertilizers", "Avoiding crop diversity", "Growing multiple types of crops in the same space", "Hard"},
                {"What is the goal of the World Food Programme (WFP)?", "Combat hunger and promote food security worldwide", "Develop new farming equipment", "Maximize agricultural exports", "Certify organic farms", "Combat hunger and promote food security worldwide", "Hard"},
                {"What is the main focus of climate-smart agriculture?", "Adapting farming to climate change while reducing emissions", "Maximizing yields regardless of climate", "Ignoring weather patterns", "Using traditional methods exclusively", "Adapting farming to climate change while reducing emissions", "Hard"},
                {"What is food sovereignty?", "The right of people to control their food systems", "Exporting surplus food globally", "Centralizing agricultural production", "Using government subsidies for farming", "The right of people to control their food systems", "Hard"},
                {"What is integrated pest management (IPM)?", "Using a combination of techniques to manage pests sustainably", "Eliminating pests with chemicals only", "Avoiding pest control altogether", "Planting only pest-resistant crops", "Using a combination of techniques to manage pests sustainably", "Hard"},
                {"What is the role of carbon sequestration in agriculture?", "Storing carbon in soil to mitigate climate change", "Releasing carbon to boost plant growth", "Burning crop residues for energy", "Removing carbon from the air for irrigation", "Storing carbon in soil to mitigate climate change", "Hard"}
                // 继续添加其他 Hard 难度的题目
        };

        for (String[] question : hardQuestions) {
            values.put(COLUMN_CONTENT, question[0]);
            values.put(COLUMN_OPTION_A, question[1]);
            values.put(COLUMN_OPTION_B, question[2]);
            values.put(COLUMN_OPTION_C, question[3]);
            values.put(COLUMN_OPTION_D, question[4]);
            values.put(COLUMN_CORRECT_ANSWER, question[5]);
            values.put(COLUMN_DIFFICULTY, question[6]);

            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }
    public void initializeProgress(String difficulty) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Progress WHERE difficulty=?", new String[]{difficulty});
        if (!cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put("difficulty", difficulty);
            values.put("questions_completed", 0);
            values.put("correct_answers", 0);
            db.insert("Progress", null, values);
        }
        cursor.close();
    }
    /*public void updateProgress(String difficulty, boolean isCorrect) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Progress SET questions_completed = questions_completed + 1, " +
                "correct_answers = correct_answers + ? WHERE difficulty = ?";
        db.execSQL(query, new Object[]{isCorrect ? 1 : 0, difficulty});
    }*/
    public void updateProgress(String difficulty, int questionsCompleted, int correctAnswers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("questions_completed", questionsCompleted);
        values.put("correct_answers", correctAnswers);

        // 更新对应难度的进度
        db.update("Progress", values, "difficulty = ?", new String[]{difficulty});
        db.close();
    }

    public Cursor getProgress(String difficulty) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Progress WHERE difficulty=?", new String[]{difficulty});
    }
    public void resetProgress(String difficulty) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Progress SET questions_completed = 0, correct_answers = 0 WHERE difficulty = ?";
        db.execSQL(query, new String[]{difficulty});
    }
    //添加专家
    public void insertExpert(String name, String specialization, double rating, String avatar, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("specialization", specialization);
        values.put("rating", rating);
        values.put("avatar", avatar);
        values.put("bio", bio);
        db.insert("Experts", null, values);
        db.close();
    }
    public void insertInitialExperts(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put("name", "Alice Green");
        values.put("specialization", "Organic Farming");
        values.put("rating", 4.8);
        values.put("avatar", "expertprofile");
        values.put("bio", "Expert in sustainable agriculture techniques.");
        db.insert("Experts", null, values);
        Log.d("DatabaseHelper", "Inserted expert: " + values.get("name"));
        values.clear(); // 清空内容，避免覆盖

        values.put("name", "Bob White");
        values.put("specialization", "Soil Management");
        values.put("rating", 4.5);
        values.put("avatar", "expertprofile");
        values.put("bio", "Specializes in soil health and fertility improvement.");
        db.insert("Experts", null, values);
        Log.d("DatabaseHelper", "Inserted expert: " + values.get("name"));
        values.clear();

        values.put("name", "Charlie Black");
        values.put("specialization", "Crop Rotation");
        values.put("rating", 4.7);
        values.put("avatar", "expertprofile");
        values.put("bio", "Focuses on crop diversification for better yield.");
        db.insert("Experts", null, values);
        Log.d("DatabaseHelper", "Inserted expert: " + values.get("name"));
        values.clear();

        values.put("name", "Diana Blue");
        values.put("specialization", "Water Conservation");
        values.put("rating", 4.9);
        values.put("avatar", "expertprofile");
        values.put("bio", "Known for water-saving irrigation techniques.");
        db.insert("Experts", null, values);
        Log.d("DatabaseHelper", "Inserted expert: " + values.get("name"));
        values.clear();

        values.put("name", "Edward Brown");
        values.put("specialization", "Pest Control");
        values.put("rating", 4.6);
        values.put("avatar", "expertprofile");
        values.put("bio", "Provides sustainable pest management solutions.");
        db.insert("Experts", null, values);
        Log.d("DatabaseHelper", "Inserted expert: " + values.get("name"));
    }
    private void insertInitialCourses(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Beginner
        values.put("title", "Soil physical properties 1");
        values.put("description", "Soil physical properties refer to characteristics like texture, structure, density, porosity, moisture, and color, which influence soil's ability to support water, air movement, and plant growth. This is the first class.");
        values.put("url", "https://www.youtube.com/watch?v=8gCWLCsFtRs&list=PLCT8-CYagFjNGGoQh6Rodvgh1PbhFrsWG&index=1");
        values.put("difficulty", "Beginner");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Soil physical properties 2");
        values.put("description", "Soil physical properties refer to characteristics like texture, structure, density, porosity, moisture, and color, which influence soil's ability to support water, air movement, and plant growth. This is the second class.");
        values.put("url", "https://www.youtube.com/watch?v=Gw8degTuOOo&list=PLCT8-CYagFjNGGoQh6Rodvgh1PbhFrsWG&index=2");
        values.put("difficulty", "Beginner");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Soil physical properties 3");
        values.put("description", "Soil physical properties refer to characteristics like texture, structure, density, porosity, moisture, and color, which influence soil's ability to support water, air movement, and plant growth. This is the third class.");
        values.put("url", "https://www.youtube.com/watch?v=Gw8degTuOOo&list=PLCT8-CYagFjNGGoQh6Rodvgh1PbhFrsWG&index=3");
        values.put("difficulty", "Beginner");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Soil physical properties 4");
        values.put("description", "Soil physical properties refer to characteristics like texture, structure, density, porosity, moisture, and color, which influence soil's ability to support water, air movement, and plant growth. This is the last class.");
        values.put("url", "https://www.youtube.com/watch?v=E2-VIRKxda0&list=PLCT8-CYagFjNGGoQh6Rodvgh1PbhFrsWG&index=4");
        values.put("difficulty", "Beginner");
        db.insert("Courses", null, values);
        values.clear();

        // Intermediate
        values.put("title", "Resources and Development");
        values.put("description", "Chapter 1 Geography NCERT class");
        values.put("url", "https://www.youtube.com/watch?v=hTT_dXVbJ40&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD");
        values.put("difficulty", "Intermediate");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Forest and Wildlife Resources");
        values.put("description", "Chapter 2 Geography NCERT Class");
        values.put("url", "https://www.youtube.com/watch?v=KnzX_uBPOcI&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD&index=2");
        values.put("difficulty", "Intermediate");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Water Resources");
        values.put("description", "Chapter 3 Geography NCERT Class");
        values.put("url", "https://www.youtube.com/watch?v=T3B_Al78-So&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD&index=3");
        values.put("difficulty", "Intermediate");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Indian Agriculture, Geography");
        values.put("description", "Major Crops of India - Rabi, Kharif, Zaid crops");
        values.put("url", "https://www.youtube.com/watch?v=IrwRM244lPQ&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD&index=4");
        values.put("difficulty", "Intermediate");
        db.insert("Courses", null, values);
        values.clear();

        // Advanced
        values.put("title", "Agriculture");
        values.put("description", "Chapter 4 Geography NCERT Class");
        values.put("url", "https://www.youtube.com/watch?v=ouANqty8Hr4&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD&index=5");
        values.put("difficulty", "Advanced");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Minerals and Energy Resources");
        values.put("description", "Chapter 5 Geography NCERT Class");
        values.put("url", "https://www.youtube.com/watch?v=L-_Byj8jMS4&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD&index=7");
        values.put("difficulty", "Advanced");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Energy Resources");
        values.put("description", "Conventional and Non-Conventional");
        values.put("url", "https://www.youtube.com/watch?v=Zgp86PVXXuQ&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD&index=8");
        values.put("difficulty", "Advanced");
        db.insert("Courses", null, values);
        values.clear();

        values.put("title", "Manufacturing Industries");
        values.put("description", "Class 10 Chapter 6 Geography NCERT");
        values.put("url", "https://www.youtube.com/watch?v=OJnWA2DF6jQ&list=PL1vNLZF5gfweoo1UZYY3hF2e0UxXuf7lD&index=9");
        values.put("difficulty", "Advanced");
        db.insert("Courses", null, values);
    }
    public List<Course> getCoursesByDifficulty(String difficulty) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Course> courseList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT title, description, url FROM Courses WHERE difficulty=?", new String[]{difficulty});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String url = cursor.getString(cursor.getColumnIndexOrThrow("url"));
                courseList.add(new Course(title, description, url));
            }
            cursor.close();
        }
        db.close();
        return courseList;
    }

    // 获取所有专家信息的方法
    public Cursor getAllExperts() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT name, specialization, rating FROM Experts", null);
    }
    public boolean isDatabaseInitialized(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0; // 如果表中已有数据，返回 true
    }
    // 插入预约记录
    public void insertAppointment(int expertId, String date, String timeSlot, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("expert_id", expertId);
        values.put("date", date);
        values.put("time_slot", timeSlot);
        values.put("status", status);

        long result = db.insert("Appointments", null, values);
        if (result == -1) {
            Log.e("DatabaseHelper", "Failed to insert appointment");
        } else {
            Log.d("DatabaseHelper", "Appointment inserted successfully");
        }
        db.close();
    }

    // 获取所有预约记录
    public Cursor getAllAppointments() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT Appointments.id, Experts.name, Appointments.date, Appointments.time_slot, Appointments.status " +
                "FROM Appointments " +
                "JOIN Experts ON Appointments.expert_id = Experts.id", null);
    }
    public Cursor getConsultationsWithExpertNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT c.id, e.name AS expert_name, c.date, c.summary, c.status " +
                "FROM Consultations c " +
                "JOIN Experts e ON c.expert_id = e.id";
        return db.rawQuery(query, null);
    }
    public Cursor getAppointmentsWithExpertNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT a.id, e.name AS expert_name, a.date, a.time_slot, a.status " +
                "FROM Appointments a " +
                "JOIN Experts e ON a.expert_id = e.id";
        return db.rawQuery(query, null);
    }

    // 获取专家ID方法
    public int getExpertIdByName(String expertName) {
        SQLiteDatabase db = this.getReadableDatabase();
        int expertId = -1; // 默认值，如果未找到返回-1

        Cursor cursor = db.rawQuery("SELECT id FROM Experts WHERE name = ?", new String[]{expertName});
        if (cursor != null && cursor.moveToFirst()) {
            expertId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            cursor.close();
        }
        return expertId;
    }
    public void deleteAppointment(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("Appointments", "id=?", new String[]{String.valueOf(id)});
        db.close();
        if (rowsAffected > 0) {
            Log.d("DatabaseHelper", "Appointment with id " + id + " deleted successfully.");
        } else {
            Log.d("DatabaseHelper", "No appointment found with id " + id);
        }
    }


}

