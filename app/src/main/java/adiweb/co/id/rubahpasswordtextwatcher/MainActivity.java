package adiweb.co.id.rubahpasswordtextwatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText passwordLama, passwordBaru, konfirPassword;
    private Button btnRubahPassword;
    private boolean hurufBesar=false, hurufKecil=false, angka=false, syimbol=false;
    private CardView cdHurufBesar, cdHurufKecil, cdAngka, cdSyimbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cdHurufBesar = (CardView) findViewById(R.id.cdHB);
        cdHurufKecil = (CardView) findViewById(R.id.cdHK);
        cdAngka = (CardView) findViewById(R.id.cdAK);
        cdSyimbol = (CardView) findViewById(R.id.cdSyim);

        passwordLama = (EditText) findViewById(R.id.pasLama);
        passwordBaru = (EditText) findViewById(R.id.passBaru);
        konfirPassword = (EditText) findViewById(R.id.konPass);
        btnRubahPassword = (Button) findViewById(R.id.ubahPassword);
        btnRubahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valUbahPass())
                    Toast.makeText(getApplicationContext(), "Password Berhasil Disimpan.!!", Toast.LENGTH_SHORT).show();
                //MENGAMBIL VARIABLE valPassChange UNTUK MEMVALIDASI PERUBAHAN PASSWORD
            }
        });
        inputChange();
        //MENGAMBIL VARIABLE inputChange UNTUK MEMBACA PASSWORD BARU PADA SAAT APLIKASI DIGUNAKAN
    }

    private boolean valUbahPass(){
        String password = getString(R.string.password);

        if (passwordLama.getText().toString().equalsIgnoreCase("") |
                passwordBaru.getText().toString().equalsIgnoreCase("") |
                konfirPassword.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(this, "Kolom Password Harus Diisi..!!", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!passwordBaru.getText().toString().equals(konfirPassword.getText().toString())){
            Toast.makeText(this, "Password Baru Tidak Sama..!!", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!passwordLama.getText().toString().equals(password)){
            Toast.makeText(this, "Password Lama Anda Salah..!!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    //MEMVALIDASI SETIAP KOLOM PASSWORD

    @SuppressLint("ResourceType")
    private void strenghtPassword(){
        String password = passwordBaru.getText().toString();
        if (password.matches("(.*[a-z].*)")){
            hurufKecil = true;
            cdHurufKecil.setCardBackgroundColor(Color.parseColor(getString(R.color.lightCheck)));
        }else {
            hurufKecil = false;
            cdHurufKecil.setCardBackgroundColor(Color.parseColor(getString(R.color.lightGrey)));
        }
        if (password.matches("(.*[A-Z].*)")){
            hurufBesar = true;
            cdHurufBesar.setCardBackgroundColor(Color.parseColor(getString(R.color.lightCheck)));
        }else{
            hurufBesar = false;
            cdHurufBesar.setCardBackgroundColor(Color.parseColor(getString(R.color.lightGrey)));
        }
        if (password.matches("(.*[0-9].*)")){
            angka = true;
            cdAngka.setCardBackgroundColor(Color.parseColor(getString(R.color.lightCheck)));
        }else{
            angka = false;
            cdAngka.setCardBackgroundColor(Color.parseColor(getString(R.color.lightGrey)));
        }
        if (password.matches("^(?=.*[~,?:;`!%^*=+{}|'/><_.()$&@]).*")){
            syimbol = true;
            cdSyimbol.setCardBackgroundColor(Color.parseColor(getString(R.color.lightCheck)));
        }else{
            syimbol = false;
            cdSyimbol.setCardBackgroundColor(Color.parseColor(getString(R.color.lightGrey)));
        }
    }
    //DEKLARASI TEKS INPUTAN PASSWORD BARU DENGAN PENCOCOKAN REGEX YANG SUDAH DISETTING DENGAN MENGGUNAKAN
    //HURUF KECIL, HURUF BESAR, ANGKA DAN SYMBOL. JIKA SALAH SATU TERPENUHI MAKA BACKRGROUND CARDVIEW AKAN BERUBAH WARNA

    private void inputChange() {
        passwordBaru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                strenghtPassword();
                if (hurufKecil && hurufBesar && angka && syimbol){
                    btnRubahPassword.setEnabled(true);
                }else{
                    btnRubahPassword.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //MEMBACA TEKS INPUTAN PASSWORD BARU, HARUS MENGGUNAKAN HURUF KECIL, HURUF BESAR, ANGKA DAN SYMBOL
    //JIKA SEMUA PERSYARATAN TERPENUHI TOMBOL SAVE AKAN AKTIF.

    public void showPassLama(View view) {
        if (view.getId()==R.id.showPassLama) {
            if (passwordLama.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) view).setImageResource(R.drawable.showpass_foreground);
                passwordLama.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else
            {
                ((ImageView) view).setImageResource(R.drawable.hidepass_foreground);
                passwordLama.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    public void showPassBaru(View view) {
        if (view.getId()==R.id.showPassBaru) {
            if (passwordBaru.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) view).setImageResource(R.drawable.showpass_foreground);
                passwordBaru.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else
            {
                ((ImageView) view).setImageResource(R.drawable.hidepass_foreground);
                passwordBaru.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    public void showKonPass(View view) {
        if (view.getId()==R.id.showKonPass) {
            if (konfirPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) view).setImageResource(R.drawable.showpass_foreground);
                konfirPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else
            {
                ((ImageView) view).setImageResource(R.drawable.hidepass_foreground);
                konfirPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
    //UNTUK MENAMPILKAN DAN MENYEMBUNYIKAN PASSWORD PADA SAAT PENGISIAN KOLOM PASSWORD
}