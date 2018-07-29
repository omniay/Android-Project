package com.example.mohamed.e_commerceproject;

/**
 * Created by Mohamed on 12/16/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





public class mydb extends SQLiteOpenHelper
{
    public static String databasename = "ee";
    SQLiteDatabase Ecommerce;

    public mydb(Context contex) {

        super(contex, databasename, null,8);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE Customers (CUST_ID INTEGER PRIMARY KEY AUTOINCREMENT ,CutName TEXT NOT NULL," +
                "UserName TEXT NOT NULL,Email TEXT NOT NULL," +
                "Password TEXT  NOT NULL,Gender TEXT NOT NULL,Job TEXT NOT NULL,BirthDay TEXT  )");


        db.execSQL("CREATE TABLE Categories (CatID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CatName TEXT NOT NULL)");

        db.execSQL("CREATE TABLE Products (PROD_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ProName TEXT NOT NULL,Price FLOAT NOT NULL,Quantity INTEGER NOT NULL," +
                "CatID INTEGER NOT NULL,FOREIGN KEY(CatID) REFERENCES Categories(CatID))");

        db.execSQL("CREATE TABLE ORDERS (ORD_ID INTEGER PRIMARY KEY AUTOINCREMENT,SUBMITED TEXT,ORD_DATE TEXT NOT NULL,CUST_ID INTEGER NOT NULL,ADDRESS TEXT NOT NULL,FOREIGN KEY(CUST_ID) REFERENCES Customers(CUST_ID))");

        db.execSQL("CREATE TABLE ORDER_DETAILS (ORD_ID INTEGER NOT NULL,PROD_ID INTEGER NOT NULL,QUANTITY INTEGER NOT NULL,PRIMARY KEY(ORD_ID,PROD_ID),FOREIGN KEY(ORD_ID) REFERENCES ORDERS(ORD_ID),FOREIGN KEY(PROD_ID) REFERENCES Products(PROD_ID))");






        db.execSQL("insert into Customers(CutName,UserName,Email,Password,Gender,Job,BirthDay) values" +
                "('Omnia','omnia','omniay646@gmail.com','o','female','student','9/4/1996')");
        db.execSQL("insert into Customers(CutName,UserName,Email,Password,Gender,Job,BirthDay) values" +
                "('ali','lul','ali@gmail.com','a','male','a','21/5/1995')");

        db.execSQL("insert into Customers(CutName,UserName,Email,Password,Gender,Job,BirthDay) values" +
                "('sabah','bo7a','sabahsamir208@gmail.com','a','female','a','21/5/1995')");

        db.execSQL("insert into Categories(CatName) values('Mobiles')");

        db.execSQL("insert into Categories(CatName) values('Laptops')");

        db.execSQL("insert into Categories(CatName) values('makeup')");

        db.execSQL("insert into Categories(CatName) values('Clothes')");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('Note4',6000,5,1)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('S7',8000,5,1)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('IPhone7',12000,5,1)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('lenovoz50',12000,5,2)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('IBOOK',28000,5,2)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('HP',8000,5,2)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('MAC Lipstick',500,2,3)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('MAC Foundation',1000,3,3)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('MAC concealer',700,3,3)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('Skirt',200,3,4)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('Pants',200,3,4)");

        db.execSQL("insert into Products(ProName,Price,Quantity,CatID) values" +
                "('Jacket',400,3,4)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS Customers ");
        db.execSQL("DROP TABLE IF EXISTS Categories ");
        db.execSQL("DROP TABLE IF EXISTS Products");
        db.execSQL("DROP TABLE IF EXISTS ORDER_DETAILS ");
        db.execSQL("DROP TABLE IF EXISTS ORDERS ");

    }

//        db.execSQL("insert into Customers(CutName,UserName,Email,Password,Gender,Job,BirthDay)

    public void Signup(String customer_name,String user_name,String email,String password
            ,String gender,String job,String birthday )
    {
        Ecommerce = getWritableDatabase();
        ContentValues new_user = new ContentValues();
        new_user.put("CutName", customer_name);
        new_user.put("UserName", user_name);
        new_user.put("Email", email);
        new_user.put("Password", password);
        new_user.put("Gender", gender);
        new_user.put("Job", job);
        new_user.put("BirthDay", birthday);
        Long ID = Ecommerce.insert("Customers", null, new_user);
        Ecommerce.close();

    }

    public boolean signin(String user, String password)
    {

        Ecommerce = getReadableDatabase();
        String[] u = {user};
        String[] p = {password};
        Cursor cursor1 = Ecommerce.rawQuery("SELECT CUST_ID FROM Customers WHERE UserName LIKE ?", u);
        Cursor cursor2 = Ecommerce.rawQuery("SELECT CUST_ID FROM Customers WHERE Password LIKE ?", p);
        cursor1.moveToFirst();
        cursor2.moveToFirst();
        if (cursor1.getCount() != 0 && cursor2.getCount() != 0)
        {
            Ecommerce.close();
            return true;


        } else
        {
            Ecommerce.close();
            return false;}
    }

    public String getcustomerID(String name)
    {
        Ecommerce=getReadableDatabase();
        String[]arg={name};
        Cursor c=Ecommerce.rawQuery("SELECT CUST_ID FROM Customers WHERE UserName LIKE ? ",arg);
        c.moveToFirst();
        Ecommerce.close();
        return c.getString(0);
    }
    public String getcustomername(String cid)
    {
        Ecommerce=getReadableDatabase();
        String[]arg={cid};
        Cursor c=Ecommerce.rawQuery("SELECT UserName FROM Customers WHERE CUST_ID LIKE ? ",arg);
        c.moveToFirst();
        Ecommerce.close();
        return c.getString(0);
    }
    public String getCatID(String Catname)
    {
        Cursor cursor;
        Ecommerce = getReadableDatabase();
        String[] catID = {Catname};
        cursor = Ecommerce.rawQuery("select CatID from Categories where CatName like ?", catID);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getString(0);

    }


    public Cursor getProducts(String catID) {
        Ecommerce = getReadableDatabase();
        String[] product = {catID};
        Cursor cursor = Ecommerce.rawQuery("select ProName from Products where CatID like ? ", product);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor;

    }

    public Cursor search(String Name) {
        Ecommerce = getReadableDatabase();
        String[] arg = {"%" + Name + "%"};
        Cursor cursor = Ecommerce.rawQuery("select ProName from Products where ProName like ?", arg);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor;
    }

    public String getProductID(String Productname) {
        Cursor cursor;
        Ecommerce = getReadableDatabase();
        //String []proid={Productname};
        String[] proid = {"%" + Productname + "%"};
        cursor = Ecommerce.rawQuery("select PROD_ID from Products where ProName like ?", proid);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getString(0);
    }


    public Cursor getProductDetails(String ID) {
        Cursor cursor;
        Ecommerce = getReadableDatabase();
        String[] details = {ID};
        cursor = Ecommerce.rawQuery("select * from Products where PROD_ID like ?", details);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor;
    }
    public String getProductname(String ID) {
        Cursor cursor;
        Ecommerce = getReadableDatabase();
        String[] details = {ID};
        cursor = Ecommerce.rawQuery("select ProName from Products where PROD_ID like ?", details);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getString(0);
    }

    public String getemail(String User)
    {
        Ecommerce = getReadableDatabase();
        String[] cutsomer = {User};
        Cursor cursor = Ecommerce.rawQuery("SELECT Email FROM Customers WHERE UserName LIKE ?", cutsomer);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getString(0);
    }
    public String getuser(String User) {
        Ecommerce = getReadableDatabase();
        String[] cutsomer = {User};
        Cursor cursor = Ecommerce.rawQuery("SELECT CUST_ID FROM Customers WHERE UserName LIKE ?", cutsomer);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getString(0);
    }

    public String getPassword(String password) {
        Ecommerce = getReadableDatabase();

        String[] cutsomer = {password};
        Cursor cursor = Ecommerce.rawQuery("SELECT CUST_ID FROM Customers WHERE Password LIKE ?", cutsomer);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getString(0);

    }
    public String returnpasswrod(String Email)
    {
        Ecommerce = getReadableDatabase();
        String[] cutsomer = {Email};
        Cursor cursor = Ecommerce.rawQuery("SELECT Password FROM Customers WHERE Email LIKE ?", cutsomer);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getString(0);
    }
    public boolean emailexist(String email)
    {
        Ecommerce = getReadableDatabase();
        String[] cutsomer = {email};
        Cursor cursor = Ecommerce.rawQuery("SELECT UserName FROM Customers WHERE Email LIKE ?", cutsomer);
        cursor.moveToFirst();
        Ecommerce.close();
        return cursor.getCount()!=0;
    }

//db.execSQL("CREATE TABLE ORDERS (ORD_ID INTEGER PRIMARY KEY AUTOINCREMENT,ORD_DATE TEXT NOT NULL,CUST_ID INTEGER NOT NULL,ADDRESS TEXT NOT NULL,FOREIGN KEY(CUST_ID) REFERENCES Customers(CUST_ID))");

    public long addorder(String date,Integer customerID,String address)
    {
        Ecommerce=getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("ORD_DATE",date);
        c.put("CUST_ID",customerID);
        c.put("ADDRESS",address);
        Long ID=Ecommerce.insert("ORDERS",null,c);
        Ecommerce.close();
        return ID;
    }
    //(ORD_ID INTEGER NOT NULL,PROD_ID INTEGER NOT NULL,QUANTITY INTEGER NOT NULL,PRIMARY KEY(ORD_ID,PROD_ID),FOREIGN KEY(ORD_ID) REFERENCES ORDERS(ORD_ID),FOREIGN KEY(PROD_ID) REFERENCES Products(PROD_ID))");
    public boolean orderdetails(Integer ORD_ID, Integer Pro_id,Integer quantity)
    {

        Ecommerce=getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("ORD_ID",ORD_ID);
        c.put("PROD_ID",Pro_id);
        c.put("QUANTITY",quantity);
        Long ID=Ecommerce.insert("ORDER_DETAILS",null,c);
        Ecommerce.close();
        return ID!=-1;
    }
    public int getquantity(String Pro_ID)
    {
        Ecommerce = getReadableDatabase();
        String[]q={Pro_ID};
        Cursor c=Ecommerce.rawQuery("select Quantity from Products where PROD_ID like ?",q);
        c.moveToFirst();
        Ecommerce.close();
        return c.getInt(0);
    }

    public boolean updatequantity(String proid,String newquantity)
    {
        Ecommerce=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("Quantity",newquantity);
        int nrow=Ecommerce.update("Products",row,"PROD_ID Like ?",new String[]{proid});
        Ecommerce.close();
        return nrow!=0;

    }
    public boolean updateorderqu(String proid,String neworderq)
    {
        Ecommerce=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("QUANTITY",neworderq);
        int nrow1=Ecommerce.update("ORDER_DETAILS",row,"PROD_ID Like ?",new String[]{proid});
        Ecommerce.close();
        return nrow1!=0;

    }
    public String getorderID(String date)
    {
        Ecommerce = getReadableDatabase();
        String[] order = {date};
        Cursor cursor = Ecommerce.rawQuery("select ORD_ID from ORDERS where ORD_DATE like ? ", order);
        cursor.moveToFirst();
        //  Ecommerce.close();
        return cursor.getString(0);
    }

    public Cursor getallorders(String Custid)
    {
        Ecommerce=getReadableDatabase();
        String[]cust={Custid};
        Cursor c=Ecommerce.rawQuery("SELECT ORD_ID FROM ORDERS  WHERE CUST_ID LIKE ? ",cust);
        c.moveToFirst();
        //      Ecommerce.close();
        return c;
    }
    public String getproductid(String orderid)
    {
        Ecommerce=getReadableDatabase();
        String[]cust={orderid};
        Cursor c=Ecommerce.rawQuery("SELECT PROD_ID FROM ORDER_DETAILS WHERE ORD_ID LIKE ? ",cust);
        c.moveToFirst();
        //  Ecommerce.close();
        return c.getString(0);

    }
    public int  orderquantity(String orderid)
    {
        Ecommerce=getReadableDatabase();
        String[]cust={orderid};
        Cursor c=Ecommerce.rawQuery("SELECT QUANTITY FROM ORDER_DETAILS WHERE ORD_ID LIKE ? ",cust);
        c.moveToFirst();
        Ecommerce.close();
        return c.getInt(0);

    }

    public void deleteorder(String orderid)
    {
        Ecommerce=getWritableDatabase();
        Ecommerce.delete("ORDERS","ORD_ID like ?",null);
        Ecommerce.delete("ORDER_DETAILS","ORD_ID like ?",null);
        Ecommerce.close();
    }
    public boolean sumbitorder(String orderid)
    {
        Ecommerce=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("SUBMITED","DONE");
        int nrow= Ecommerce.update("ORDERS",row,"ORD_ID Like ?",new String[]{orderid});
        //  Ecommerce.insert("ORDERS","ORD_ID like ?", row);
        Ecommerce.close();
        return nrow!=0;

    }
    public String Issubmited(String orderid)
    {
        Ecommerce=getReadableDatabase();
        String[]cust={orderid};
        Cursor c=Ecommerce.rawQuery("SELECT SUBMITED FROM ORDERS WHERE ORD_ID LIKE ? ",cust);
        c.moveToFirst();
        //  Ecommerce.close();

        return c.getString(0);
    }

}


