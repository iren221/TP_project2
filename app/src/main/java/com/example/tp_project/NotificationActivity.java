package com.example.tp_project;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationActivity extends AppCompatActivity {

    CheckBox chek_eat, chek_water, chek_apteka, chek_walk, chek_ches, chek_kup;

    private static final String CHANNEL_ID = "pet_channel";
    private static final String CHANNEL_ID_WATER = "pet_channel_water";
    private static final String CHANNEL_ID_APTEKA = "pet_channel_apteka";
    private static final String CHANNEL_ID_WALK = "pet_channel_walk";
    private static final String CHANNEL_ID_CHES = "pet_channel_ches";
    private static final String CHANNEL_ID_KUP = "pet_channel_kup";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        chek_eat = findViewById(R.id.chek_eat);
        chek_water = findViewById(R.id.chek_water);
        chek_apteka = findViewById(R.id.chek_apteka);
        chek_walk = findViewById(R.id.chek_walk);
        chek_ches = findViewById(R.id.chek_ches);
        chek_kup = findViewById(R.id.chek_kup);

//        if (chek_eat.isChecked()) {
//            Toast.makeText(this, "Вы выбрали включить уведомления на еду", Toast.LENGTH_SHORT).show();
//        }
        Animal animal = getIntent().getParcelableExtra("animal");
        String key = animal.getId();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("animals").child(uid).child(key);
        //еда
        chek_eat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chek_eat.isChecked()){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Integer b1_state = dataSnapshot.child("eat").getValue(Integer.class); // получаем данные о состоянии кнопки b1
                                if (b1_state != null) {
                                    if (b1_state == 0) {
                                        Toast.makeText(getApplicationContext(), "Вы включили уведомления на еду", Toast.LENGTH_SHORT).show();
                                        if (areNotificationsEnabled()) {
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    showNotification();
                                                }
                                            }, 10000);
                                            //showNotification();
                                        } else {
                                            // Notifications are not enabled, request permission
                                            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());

                                            startActivity(intent);
                                        }
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Кнопка не отжата", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("getb1", "onCancelled", databaseError.toException());
                        }
                    });

                }
            }
        });
        //питьё
        chek_water.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chek_water.isChecked()){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Integer b1_state = dataSnapshot.child("kupanie").getValue(Integer.class); // получаем данные о состоянии кнопки b1
                                if (b1_state != null) {
                                    if (b1_state == 0) {
                                        Toast.makeText(getApplicationContext(), "Вы включили уведомления на питьё", Toast.LENGTH_SHORT).show();
                                        if (areNotificationsEnabled()) {
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    showNotification2();
                                                }
                                            }, 10050);
                                            //showNotification2();
                                        } else {
                                            // Notifications are not enabled, request permission
                                            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());

                                            startActivity(intent);
                                        }
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Кнопка не отжата", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("getb2", "onCancelled", databaseError.toException());
                        }
                    });

                }
            }
        });
        //лекарства
        chek_apteka.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chek_apteka.isChecked()){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Integer b1_state = dataSnapshot.child("apteka").getValue(Integer.class); // получаем данные о состоянии кнопки b1
                                if (b1_state != null) {
                                    if (b1_state == 0) {
                                        Toast.makeText(getApplicationContext(), "Вы включили уведомления на лекарства", Toast.LENGTH_SHORT).show();
                                        if (areNotificationsEnabled()) {
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    showNotification3();
                                                }
                                            }, 10100);
                                            //showNotification3();
                                        } else {
                                            // Notifications are not enabled, request permission
                                            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());

                                            startActivity(intent);
                                        }
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Кнопка не отжата", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("getb3", "onCancelled", databaseError.toException());
                        }
                    });

                }
            }
        });
        //гуляние
        chek_walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chek_walk.isChecked()){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Integer b1_state = dataSnapshot.child("walk").getValue(Integer.class); // получаем данные о состоянии кнопки b1
                                if (b1_state != null) {
                                    if (b1_state == 0) {
                                        Toast.makeText(getApplicationContext(), "Вы включили уведомления на прогулку", Toast.LENGTH_SHORT).show();
                                        if (areNotificationsEnabled()) {
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    showNotification4();
                                                }
                                            }, 10150);
                                            //showNotification4();
                                        } else {
                                            // Notifications are not enabled, request permission
                                            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());

                                            startActivity(intent);
                                        }
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Кнопка не отжата", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("getb4", "onCancelled", databaseError.toException());
                        }
                    });

                }
            }
        });
        // стрижка

        chek_ches.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chek_ches.isChecked()){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Integer b1_state = dataSnapshot.child("grumer").getValue(Integer.class); // получаем данные о состоянии кнопки b1
                                if (b1_state != null) {
                                    if (b1_state == 0) {
                                        Toast.makeText(getApplicationContext(), "Вы включили уведомления на вычёсывание", Toast.LENGTH_SHORT).show();
                                        if (areNotificationsEnabled()) {
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    showNotification5();
                                                }
                                            }, 10200);
                                            //showNotification5();
                                        } else {
                                            // Notifications are not enabled, request permission
                                            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());

                                            startActivity(intent);
                                        }
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Кнопка не отжата", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("getb5", "onCancelled", databaseError.toException());
                        }
                    });

                }
            }
        });

        //купание

        chek_kup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chek_kup.isChecked()){
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Integer b1_state = dataSnapshot.child("puzirek").getValue(Integer.class); // получаем данные о состоянии кнопки b1
                                if (b1_state != null) {
                                    if (b1_state == 0) {
                                        Toast.makeText(getApplicationContext(), "Вы включили уведомления на вычёсывание", Toast.LENGTH_SHORT).show();
                                        if (areNotificationsEnabled()) {
                                            Handler handler = new Handler(Looper.getMainLooper());
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    showNotification6();
                                                }
                                            }, 10250);
                                            //showNotification6();
                                        } else {
                                            // Notifications are not enabled, request permission
                                            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());

                                            startActivity(intent);
                                        }
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Кнопка не отжата", Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("getb6", "onCancelled", databaseError.toException());
                        }
                    });

                }
            }
        });
//        chek_water.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (chek_water.isChecked()){
//                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            if (dataSnapshot.exists()) {
//                                Integer b1_state = dataSnapshot.child("water").getValue(Integer.class); // получаем данные о состоянии кнопки b1
//                                if (b1_state != null) {
//                                    if (b1_state == 0) {
//                                        Toast.makeText(getApplicationContext(), "Вы включили уведомления на питьё", Toast.LENGTH_SHORT).show();
//                                        if (areNotificationsEnabled()) {
//                                            showNotification2();
//                                        } else {
//                                            // Notifications are not enabled, request permission
//                                            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
//                                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//
//                                            startActivity(intent);
//                                        }
//                                    }
//                                } else {
//                                    Toast.makeText(getApplicationContext(), "Кнопка не отжата", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            Log.w("getb2", "onCancelled", databaseError.toException());
//                        }
//                    });
//                }
//            }
//        });

//        chek_apteka.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (chek_apteka.isChecked()){
//                    Toast.makeText(getApplicationContext(), "Вы включили уведомления на лекарства", Toast.LENGTH_SHORT).show();
//                    if (areNotificationsEnabled()) {
//                        showNotification3();
//                    } else {
//                        // Notifications are not enabled, request permission
//                        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//
//                        startActivity(intent);
//                    }
//                }
//            }
//        });

//        chek_walk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (chek_walk.isChecked()){
//                    Toast.makeText(getApplicationContext(), "Вы включили уведомления на прогулку", Toast.LENGTH_SHORT).show();
//                    if (areNotificationsEnabled()) {
//                        showNotification4();
//                    } else {
//                        // Notifications are not enabled, request permission
//                        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//
//                        startActivity(intent);
//                    }
//                }
//            }
//        });
//
//        chek_ches.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (chek_ches.isChecked()){
//                    Toast.makeText(getApplicationContext(), "Вы включили уведомления на вычёсывание", Toast.LENGTH_SHORT).show();
//                    if (areNotificationsEnabled()) {
//                        showNotification5();
//                    } else {
//                        // Notifications are not enabled, request permission
//                        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//
//                        startActivity(intent);
//                    }
//                }
//            }
//        });
//
//        chek_kup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (chek_kup.isChecked()){
//                    Toast.makeText(getApplicationContext(), "Вы включили уведомления на купание", Toast.LENGTH_SHORT).show();
//                    if (areNotificationsEnabled()) {
//                        showNotification6();
//                    } else {
//                        // Notifications are not enabled, request permission
//                        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                                .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//
//                        startActivity(intent);
//                    }
//                }
//            }
//        });

//        if (areNotificationsEnabled()) {
//
//            showNotification();
//        } else {
//            // Notifications are not enabled, request permission
//            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
//                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    .putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//
//            startActivity(intent);
//        }
//        }


    }


    private boolean areNotificationsEnabled() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        return notificationManagerCompat.areNotificationsEnabled();
    }

    private void showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Pet Eat",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Кормление")
                .setContentText("Не забудь покормить своих питомцев!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(1, notification);
    }

    private void showNotification2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_WATER,
                    "Pet Water",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_WATER)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Питьё")
                .setContentText("Не забудь напоить своих питомцев!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(2, notification);
    }

    private void showNotification3() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_APTEKA,
                    "Pet Apteka",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_APTEKA)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Лекарства")
                .setContentText("Не забудь дать лекарства своим питомцам!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(3, notification);
    }

    private void showNotification4() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_WALK,
                    "Pet Walk",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_WALK)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Пора гулять")
                .setContentText("Не забудь погулять со своими питомцами!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(4, notification);
    }

    private void showNotification5() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_CHES,
                    "Pet Ches",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_CHES)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Почеши меня")
                .setContentText("Не забудь вычесать своих питомцев!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(5, notification);
    }

    private void showNotification6() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID_KUP,
                    "Pet Kup",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_KUP)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Искупай меня")
                .setContentText("Не забудь искупать своих питомцев!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(6, notification);
    }
}
