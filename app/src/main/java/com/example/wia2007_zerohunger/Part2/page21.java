package com.example.wia2007_zerohunger.Part2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.example.wia2007_zerohunger.R;

public class page21 extends AppCompatActivity {

    protected static String savedTakeUpAddressForMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page21);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button page21_backBtnP2 = findViewById(R.id.page21_backBtnP2);

        page21_backBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (page21.this, page19.class);
                startActivity(intent);
            }
        });

        // Spinner 1
        // Our list of options for donation type
        List<String> optionsForDonationP2 = Arrays.asList("Select your donation type","Organization", "Individual", "Shop", "Supermarket");

        // Adapter1 for the Spinner1
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Default simple layout
                optionsForDonationP2
        );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Dropdown Layout

        Spinner page21_donationTypeSpinnerP2 = findViewById(R.id.page21_donationTypeP2);
        page21_donationTypeSpinnerP2.setAdapter(adapter1);

        // Optional: Set an item selection listener
        page21_donationTypeSpinnerP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected: "+selectedOption, Toast.LENGTH_SHORT).show();
            }

            String selectedOption = page21_donationTypeSpinnerP2.getSelectedItem().toString();
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
                if (selectedOption.equals("Select your donation type")){
                    Toast.makeText(page21.this, "This field must be filled!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Spinner 2
        // Our list of options for Community Kitchen type
        List<String> optionsForCommunityKitchen = Arrays.asList("Select our reliable Community Kitchen","Springfield Kitchen","Rivertown","Lakeside Diner","Hillcrest Hub","Valleyview Kitchen","Greenwood Eats");

        // Adapter2 for the Spinner2
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Default simple layout
                optionsForCommunityKitchen
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Dropdown Layout

        Spinner page21_CommunityKitchenSpinnerP2 = findViewById(R.id.page21_CommunityKitchenSpinnerP2);
        page21_CommunityKitchenSpinnerP2.setAdapter(adapter2);

        // Optional: Set an item selection listener
        page21_CommunityKitchenSpinnerP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected: "+selectedOption, Toast.LENGTH_SHORT).show();
            }

            String selectedOption = page21_CommunityKitchenSpinnerP2.getSelectedItem().toString();
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
                if (selectedOption.equals("Select our reliable Community Kitchen")){
                    Toast.makeText(page21.this, "This field must be filled!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Spinner 3
        // Our list of options for Available Period for us to take the item
        List<String>  optionsForAvailablePeriodP2 = Arrays.asList("Select your available period for us to take the item","10.00am.","12.00pm.","3.00pm.","5.00pm.","7.00pm.");

        // Adapter3 for the Spinner3
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Default simple layout
                optionsForAvailablePeriodP2
        );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Dropdown Layout

        Spinner page21_availablePeriodSpinnerP2 = findViewById(R.id.page21_availablePeriodSpinnerP2);
        page21_availablePeriodSpinnerP2.setAdapter(adapter3);

        // Optional: Set an item selection listener
        page21_availablePeriodSpinnerP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected: "+selectedOption, Toast.LENGTH_SHORT).show();
            }

            String selectedOption = page21_availablePeriodSpinnerP2.getSelectedItem().toString();
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
                if (selectedOption.equals("Select your available period for us to take the item")){
                    Toast.makeText(page21.this, "This field must be filled!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Spinner 4
        // Our list of options for Suitable Transport Method based on the item quantity
        List<String>  optionsForTransportMethodP2 = Arrays.asList("Select the suitable transport method based on your item quantity","Car ","1-Ton Truck","3-Ton Truck","10-Ton Truck");

        // Adapter4 for the Spinner4
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // Default simple layout
                optionsForTransportMethodP2
        );
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Dropdown Layout

        Spinner page21_transportMethodSpinnerP2 = findViewById(R.id.page21_transportMethodSpinnerP2);
        page21_transportMethodSpinnerP2.setAdapter(adapter4);

        // Optional: Set an item selection listener
        page21_transportMethodSpinnerP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Selected: "+selectedOption, Toast.LENGTH_SHORT).show();
            }

            String selectedOption = page21_transportMethodSpinnerP2.getSelectedItem().toString();
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
                if (selectedOption.equals("Select the suitable transport method based on your item quantity")){
                    Toast.makeText(page21.this, "This field must be filled!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button page21_detailBtnP2 = findViewById(R.id.page21_detailBtnP2);
        page21_detailBtnP2.setVisibility(View.GONE); // Initially hide the button

        page21_detailBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page21.this, GoogleMap_P2.class);
                startActivity(intent);
            }
        });

        TextView page21_fullOrganizationNameP2 = findViewById(R.id.page21_fullOrganizationNameP2);
        TextView page21_emailP2 = findViewById(R.id.page21_emailP2);
        TextView page21_phoneNumberP2 = findViewById(R.id.page21_phoneNumberP2);
        TextView page21_takeupAddressP2 = findViewById(R.id.page21_takeupAddressP2);
        TextView page21_remarkP2 = findViewById(R.id.page21_remarkP2);

        Button page21_submitBtnP2 = findViewById(R.id.page21_submitBtnP2);
        page21_submitBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String organizationName = page21_fullOrganizationNameP2.getText().toString();
                String email = page21_emailP2.getText().toString();
                String phoneNumber = page21_phoneNumberP2.getText().toString();
                String takeupAddress = page21_takeupAddressP2.getText().toString();
                String remark = page21_remarkP2.getText().toString();

                // Spinner selections
                String donationType = page21_donationTypeSpinnerP2.getSelectedItem().toString();
                String communityKitchen = page21_CommunityKitchenSpinnerP2.getSelectedItem().toString();
                String availablePeriod = page21_availablePeriodSpinnerP2.getSelectedItem().toString();
                String transportMethod = page21_transportMethodSpinnerP2.getSelectedItem().toString();

                // Check if all required fields except remark are filled
                if (organizationName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || takeupAddress.isEmpty() ||
                        donationType.equals("Select your donation type") || communityKitchen.equals("Select our reliable Community Kitchen") || availablePeriod.equals("Select your available period for us to take the item") || transportMethod.equals("Select the suitable transport method based on your item quantity")) {
                    Toast.makeText(page21.this, "Please fill in all required fields (excluding remarks).", Toast.LENGTH_SHORT).show();
                } else {

                    savedTakeUpAddressForMap = takeupAddress;

                    Toast.makeText(page21.this, "Submission successful! Your information has been sent.", Toast.LENGTH_SHORT).show();

                    // Save text from TextViews
                    String savedOrganizationName = organizationName;
                    String savedEmail = email;
                    String savedPhoneNumber = phoneNumber;
                    String savedTakeupAddress = takeupAddress;
                    String savedRemark = remark;

                    // Store the current spinner selections
                    String savedDonationType = donationType;
                    String savedCommunityKitchen = communityKitchen;
                    String savedAvailablePeriod = availablePeriod;
                    String savedTransportMethod = transportMethod;

                    // Make the Details button visible after submission
                    page21_detailBtnP2.setVisibility(View.VISIBLE);

                    resetFields();

                    DatabaseHelper_P2 dbHelper = new DatabaseHelper_P2(page21.this);

                    // Get a writable database instance
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    // Save data
                    long rowId = dbHelper.insertData(
                            savedOrganizationName,
                            savedEmail,
                            savedPhoneNumber,
                            savedTakeupAddress,
                            savedRemark,
                            savedDonationType,
                            savedCommunityKitchen,
                            savedAvailablePeriod,
                            savedTransportMethod
                    );

                    // Optional: Show a message or log the row ID
                    Log.d("DatabaseHelper", "Data inserted with row ID: " + rowId);

                    if (rowId != -1) {
                        Toast.makeText(page21.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(page21.this, "Failed to save data.", Toast.LENGTH_SHORT).show();
                    }


                    String subject = "Your Food Donation Makes a Difference - A Message from ZeroHungerHub";
                    // Construct the email content
                    String emailContent = "Thank you for your generous donation! Your support makes a meaningful difference in the lives of those in need."+
                            "\n\nDonation information as below: " +
                            "\nOrganization Name: " + savedOrganizationName +
                            "\nDonation Type: " + savedDonationType +
                            "\nCommunity Kitchen: " + savedCommunityKitchen +
                            "\nAvailable Period: " + savedAvailablePeriod +
                            "\nTransport Method: " + savedTransportMethod +
                            "\nPhone Number: " + savedPhoneNumber +
                            "\nTakeup Address: " + savedTakeupAddress +
                            "\nRemark: " + savedRemark +
                            "\n\nBest Regards," +
                            "\nZeroHungerHub";

                    sendEmail(savedEmail,subject,emailContent);
                }
            }
            private void resetFields() {
                page21_fullOrganizationNameP2.setText("");
                page21_emailP2.setText("");
                page21_phoneNumberP2.setText("");
                page21_takeupAddressP2.setText("");
                page21_remarkP2.setText("");

                page21_donationTypeSpinnerP2.setSelection(0); // Reset spinner
                page21_CommunityKitchenSpinnerP2.setSelection(0); // Reset spinner
                page21_availablePeriodSpinnerP2.setSelection(0); // Reset spinner
                page21_transportMethodSpinnerP2.setSelection(0); // Reset spinner
            }

            private void sendEmail(String recipientEmail, String subject, String content) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(recipientEmail).matches()) {
                    final String senderEmail = "seechansing9599@gmail.com"; // Replace with your email
                    final String senderPassword = "j5UW/3Ni"; // Replace with your email password or app password

                    // Configure mail server properties
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");

                    // Create a session with the mail server
                    Session session = Session.getInstance(props, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderEmail, senderPassword);
                        }
                    });

                    try {
                        // Create a new email message
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(senderEmail));
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                        message.setSubject(subject);
                        message.setText(content);

                        // Send the email
                        Transport.send(message);
                        runOnUiThread(() -> Toast.makeText(page21.this, "Email sent successfully!", Toast.LENGTH_SHORT).show());
                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(() -> Toast.makeText(page21.this, "Failed to send email.", Toast.LENGTH_SHORT).show());
                    }
                } else {
                    Toast.makeText(page21.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}