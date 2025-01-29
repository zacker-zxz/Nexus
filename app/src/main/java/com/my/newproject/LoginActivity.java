package com.my.newproject;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.io.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.regex.*;
import org.json.*;

public class LoginActivity extends AppCompatActivity {
	
	private boolean isVisible = false;
	
	private ScrollView vscroll2;
	private RelativeLayout linear1;
	private LinearLayout linear2;
	private LinearLayout signin;
	private LinearLayout signup;
	private LinearLayout forgot_page;
	private TextView textview5;
	private TextInputLayout textinputlayout_username;
	private TextInputLayout textinputlayout_email;
	private TextInputLayout textinputlayout_password;
	private Button button1;
	private TextView textview9;
	private LinearLayout linear5;
	private TextView textview_already;
	private EditText edittext_username;
	private EditText edittext_email;
	private EditText edittext_password;
	private ImageView imageview1;
	private ImageView imageview4;
	private ImageView imageview3;
	private ImageView imageview2;
	private TextView textview1;
	private TextInputLayout textinputlayout_email2;
	private TextInputLayout textinputlayout_password2;
	private TextView textview_forget;
	private TextView textview_resend;
	private Button button_login;
	private TextView textview10;
	private LinearLayout linear6;
	private TextView textview_dont_have_account;
	private EditText edittext_email2;
	private EditText edittext_password2;
	private ImageView imageview5;
	private ImageView imageview6;
	private ImageView imageview7;
	private ImageView imageview8;
	private TextView textview6;
	private TextInputLayout textinputlayout_email3;
	private TextView textview_get_help;
	private Button verification_button;
	private TextView textview_back_to_login;
	private EditText edittext_email3;
	
	private Calendar calendar = Calendar.getInstance();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private SharedPreferences sharedPreferences;
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll2 = findViewById(R.id.vscroll2);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		signin = findViewById(R.id.signin);
		signup = findViewById(R.id.signup);
		forgot_page = findViewById(R.id.forgot_page);
		textview5 = findViewById(R.id.textview5);
		textinputlayout_username = findViewById(R.id.textinputlayout_username);
		textinputlayout_email = findViewById(R.id.textinputlayout_email);
		textinputlayout_password = findViewById(R.id.textinputlayout_password);
		button1 = findViewById(R.id.button1);
		textview9 = findViewById(R.id.textview9);
		linear5 = findViewById(R.id.linear5);
		textview_already = findViewById(R.id.textview_already);
		edittext_username = findViewById(R.id.edittext_username);
		edittext_email = findViewById(R.id.edittext_email);
		edittext_password = findViewById(R.id.edittext_password);
		imageview1 = findViewById(R.id.imageview1);
		imageview4 = findViewById(R.id.imageview4);
		imageview3 = findViewById(R.id.imageview3);
		imageview2 = findViewById(R.id.imageview2);
		textview1 = findViewById(R.id.textview1);
		textinputlayout_email2 = findViewById(R.id.textinputlayout_email2);
		textinputlayout_password2 = findViewById(R.id.textinputlayout_password2);
		textview_forget = findViewById(R.id.textview_forget);
		textview_resend = findViewById(R.id.textview_resend);
		button_login = findViewById(R.id.button_login);
		textview10 = findViewById(R.id.textview10);
		linear6 = findViewById(R.id.linear6);
		textview_dont_have_account = findViewById(R.id.textview_dont_have_account);
		edittext_email2 = findViewById(R.id.edittext_email2);
		edittext_password2 = findViewById(R.id.edittext_password2);
		imageview5 = findViewById(R.id.imageview5);
		imageview6 = findViewById(R.id.imageview6);
		imageview7 = findViewById(R.id.imageview7);
		imageview8 = findViewById(R.id.imageview8);
		textview6 = findViewById(R.id.textview6);
		textinputlayout_email3 = findViewById(R.id.textinputlayout_email3);
		textview_get_help = findViewById(R.id.textview_get_help);
		verification_button = findViewById(R.id.verification_button);
		textview_back_to_login = findViewById(R.id.textview_back_to_login);
		edittext_email3 = findViewById(R.id.edittext_email3);
		auth = FirebaseAuth.getInstance();
		sharedPreferences = getSharedPreferences("username", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String username = edittext_username.getText().toString().trim();
				String email = edittext_email.getText().toString().trim();
				String password = edittext_password.getText().toString().trim();
				if (!_isValidEmail(email)) {
					((EditText)edittext_email).setError("Please enter a valid email");
					return;
				}
				else {
					((EditText)edittext_email).setError(null);
				}
				if (!_inValidPassword(password)) {
					((EditText)edittext_password).setError("Password must be at least 8 characters long, contain at least one lowercase, one uppercase, one number, and one special character");
					return;
				}
				else {
					((EditText)edittext_password).setError(null);
				}
				if (username.isEmpty() && (4 < username.length())) {
					((EditText)edittext_username).setError("Please enter a valid username");
					return;
				}
				else {
					((EditText)edittext_username).setError(null);
				}
				auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, _auth_create_user_listener);
				sharedPreferences.edit().putString("email", email).commit();
				sharedPreferences.edit().putString("password", password).commit();
				sharedPreferences.edit().putString("username", username).commit();
				Toast.makeText(LoginActivity.this, "Account creating...", Toast.LENGTH_SHORT).show();
			}
		});
		
		textview_already.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_transition(linear1);
				signin.setVisibility(View.GONE);
				signup.setVisibility(View.VISIBLE);
				isVisible = !isVisible;
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		textview_forget.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_transition(linear1);
				signup.setVisibility(View.GONE);
				forgot_page.setVisibility(View.VISIBLE);
			}
		});
		
		textview_resend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(auth_emailVerificationSentListener);
			}
		});
		
		button_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String email = edittext_email2.getText().toString().trim();
				String password = edittext_password2.getText().toString().trim();
				if (!_inValidPassword(password)) {
					((EditText)edittext_password2).setError("Password must be at least 8 characters long, contain at least one lowercase, one uppercase, one number, and one special character");
					return;
				}
				else {
					((EditText)edittext_password2).setError(null);
				}
				if (!_isValidEmail(email)) {
					if (sharedPreferences.getString("username", "").equals(email)) {
						Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(), HomeActivity.class);
						startActivity(intent);
						onBackPressed();
						return;
					}
				}
				else {
					((EditText)edittext_email2).setError(null);
					auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, _auth_sign_in_listener);
					intent.setClass(getApplicationContext(), HomeActivity.class);
					startActivity(intent);
				}
			}
		});
		
		textview_dont_have_account.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_transition(linear1);
				signup.setVisibility(View.GONE);
				signin.setVisibility(View.VISIBLE);
				isVisible = !isVisible;
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		textview_get_help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		verification_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String email = edittext_email3.getText().toString().trim();
				if (!_isValidEmail(email)) {
					((EditText)edittext_email3).setError("Please enter a valid email");
					return;
				}
				else {
					((EditText)edittext_email3).setError(null);
				}
				auth.sendPasswordResetEmail(email).addOnCompleteListener(_auth_reset_password_listener);
				Toast.makeText(LoginActivity.this, "Reset password link sending...", Toast.LENGTH_SHORT).show();
			}
		});
		
		textview_back_to_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_transition(linear1);
				forgot_page.setVisibility(View.GONE);
				signin.setVisibility(View.GONE);
				signup.setVisibility(View.VISIBLE);
				isVisible = true;
			}
		});
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					Toast.makeText(LoginActivity.this, "Verification link is been sended to your email.", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(LoginActivity.this, _errorMessage, Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					Toast.makeText(LoginActivity.this, "Account created Successfully", Toast.LENGTH_SHORT).show();
					FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(auth_emailVerificationSentListener);
					signin.setVisibility(View.GONE);
					signup.setVisibility(View.VISIBLE);
					isVisible = !isVisible;
				}
				else {
					Toast.makeText(LoginActivity.this, _errorMessage, Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					boolean isVerified = auth.getCurrentUser().isEmailVerified();
					if (isVerified) {
						Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(), HomeActivity.class);
						startActivity(intent);
						onBackPressed();
					}
					else {
						textview_resend.setText("send verification link");
						textview_resend.setVisibility(View.VISIBLE);
						Toast.makeText(LoginActivity.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
					}
				}
				else {
					Toast.makeText(LoginActivity.this, _errorMessage, Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				if (_success) {
					Toast.makeText(LoginActivity.this, "Reset password link sended on your email!.", Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(LoginActivity.this, "Reset password link send failed please try later!", Toast.LENGTH_SHORT).show();
				}
			}
		};
	}
	
	private void initializeLogic() {
		signin.setElevation((float)10);
		signup.setElevation((float)10);
		forgot_page.setElevation((float)10);
		_windowTransparent();
	}
	
	public void _transition(final View _view) {
		RelativeLayout viewgroup =(RelativeLayout) _view;  
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition();  
		autoTransition.setDuration((long) 300);  
		android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _windowTransparent() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				Window w = getWindow();
				w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
		
	}
	
	
	public boolean _isValidEmail(final String _email) {
		return (!_email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(_email).matches());
	}
	
	
	public boolean _inValidPassword(final String _password) {
		Pattern password = Pattern.compile("^" + 
		"(?=.*[0-9])" + 
		"(?=.*[a-z])" +
		"(?=.*[A-Z])" +
		"(?=.*[@#$%^&+=!])" +
		"(?=\\S+$)" +
		".{8,}" +
		"$"
		);
		return (!_password.isEmpty() && password.matcher(_password).matches());
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}