package com.my.newproject;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import org.json.*;

public class Homepage2Activity extends AppCompatActivity {
	
	public final int REQ_CD_GHH = 101;
	
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private String Path = "";
	private String Path_name = "";
	
	private ArrayList<String> filepath = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private TextView textview1;
	private TextView textview2;
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private LinearLayout _drawer_linear10;
	private LinearLayout _drawer_linear11;
	private LinearLayout _drawer_linear12;
	private ImageView _drawer_imageview2;
	private LinearLayout _drawer_linear19;
	private TextView _drawer_textview4;
	private LinearLayout _drawer_linear13;
	private LinearLayout _drawer_linear14;
	private LinearLayout _drawer_linear15;
	private LinearLayout _drawer_linear16;
	private LinearLayout _drawer_linear17;
	private LinearLayout _drawer_linear18;
	private TextView _drawer_textview6;
	private TextView _drawer_textview5;
	private TextView _drawer_textview8;
	private TextView _drawer_textview7;
	private TextView _drawer_textview9;
	private TextView _drawer_textview10;
	
	private Intent in1 = new Intent();
	private FirebaseAuth fba;
	private OnCompleteListener<AuthResult> _fba_create_user_listener;
	private OnCompleteListener<AuthResult> _fba_sign_in_listener;
	private OnCompleteListener<Void> _fba_reset_password_listener;
	private OnCompleteListener<Void> fba_updateEmailListener;
	private OnCompleteListener<Void> fba_updatePasswordListener;
	private OnCompleteListener<Void> fba_emailVerificationSentListener;
	private OnCompleteListener<Void> fba_deleteUserListener;
	private OnCompleteListener<Void> fba_updateProfileListener;
	private OnCompleteListener<AuthResult> fba_phoneAuthListener;
	private OnCompleteListener<AuthResult> fba_googleSignInListener;
	
	private Intent ghh = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference fbs = _firebase_storage.getReference("fbs");
	private OnCompleteListener<Uri> _fbs_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbs_download_success_listener;
	private OnSuccessListener _fbs_delete_success_listener;
	private OnProgressListener _fbs_upload_progress_listener;
	private OnProgressListener _fbs_download_progress_listener;
	private OnFailureListener _fbs_failure_listener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.homepage2);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
//	akmkc
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = findViewById(R.id._fab);
		
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(Homepage2Activity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		imageview1 = findViewById(R.id.imageview1);
		imageview2 = findViewById(R.id.imageview2);
		imageview3 = findViewById(R.id.imageview3);
		imageview4 = findViewById(R.id.imageview4);
		_drawer_linear10 = _nav_view.findViewById(R.id.linear10);
		_drawer_linear11 = _nav_view.findViewById(R.id.linear11);
		_drawer_linear12 = _nav_view.findViewById(R.id.linear12);
		_drawer_imageview2 = _nav_view.findViewById(R.id.imageview2);
		_drawer_linear19 = _nav_view.findViewById(R.id.linear19);
		_drawer_textview4 = _nav_view.findViewById(R.id.textview4);
		_drawer_linear13 = _nav_view.findViewById(R.id.linear13);
		_drawer_linear14 = _nav_view.findViewById(R.id.linear14);
		_drawer_linear15 = _nav_view.findViewById(R.id.linear15);
		_drawer_linear16 = _nav_view.findViewById(R.id.linear16);
		_drawer_linear17 = _nav_view.findViewById(R.id.linear17);
		_drawer_linear18 = _nav_view.findViewById(R.id.linear18);
		_drawer_textview6 = _nav_view.findViewById(R.id.textview6);
		_drawer_textview5 = _nav_view.findViewById(R.id.textview5);
		_drawer_textview8 = _nav_view.findViewById(R.id.textview8);
		_drawer_textview7 = _nav_view.findViewById(R.id.textview7);
		_drawer_textview9 = _nav_view.findViewById(R.id.textview9);
		_drawer_textview10 = _nav_view.findViewById(R.id.textview10);
		fba = FirebaseAuth.getInstance();
		ghh.setType("ghh");
		ghh.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), JobApplyActivity.class);
				startActivity(in1);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), WebsiteActivity.class);
				startActivity(in1);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), LearningActivity.class);
				startActivity(in1);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), ChatbotActivity.class);
				startActivity(in1);
			}
		});
		
		_fbs_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_fbs_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_fbs_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_fbs_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_fbs_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_fbs_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_drawer_textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(in1);
			}
		});
		
		_drawer_textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), WebsiteActivity.class);
				startActivity(in1);
			}
		});
		
		_drawer_textview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), ChatbotActivity.class);
				startActivity(in1);
			}
		});
		
		_drawer_textview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), MapsActivity.class);
				startActivity(in1);
			}
		});
		
		fba_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fba_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fba_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fba_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fba_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fba_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fba_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fba_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fba_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fba_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		_setcornerradius(linear2, 75, 12, "#FFFFFF");
		_setcornerradius(linear4, 75, 12, "#FFFFFF");
		_setcornerradius(linear5, 75, 12, "#FFFFFF");
		_setcornerradius(_drawer_linear11, 50, 38, "#18FFFF");
		_setcornerradius(_drawer_textview4, 50, 38, "#FFFFFF");
		_setcornerradius(_drawer_imageview2, 50, 38, "#FFFFFF");
		_drawer_textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), Homepage3Activity.class);
				startActivity(in1);
			}
		});
		_drawer_textview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in1.setClass(getApplicationContext(), MapsActivity.class);
				startActivity(in1);
			}
		});
		Bitmap bm = ((android.graphics.drawable.BitmapDrawable) _drawer_imageview2.getDrawable()).getBitmap();
		
	}
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) { Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888); Canvas canvas = new Canvas(output); final int color = 0xff424242; final Paint paint = new Paint(); final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); final RectF rectF = new RectF(rect); final float roundPx = pixels; paint.setAntiAlias(true); canvas.drawARGB(0, 0, 0, 0); paint.setColor(color); canvas.drawRoundRect(rectF, roundPx, roundPx, paint); paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); canvas.drawBitmap(bitmap, rect, rect, paint); return output;
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_GHH:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				Path = filepath.get((int)(0));
				Path_name = Uri.parse(Uri.parse(Path).getLastPathSegment()).getLastPathSegment();
				fbs.child(Path_name).putFile(Uri.fromFile(new File(Path))).addOnFailureListener(_fbs_failure_listener).addOnProgressListener(_fbs_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
					@Override
					public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
						return fbs.child(Path_name).getDownloadUrl();
					}}).addOnCompleteListener(_fbs_upload_success_listener);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	public void _setcornerradius(final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		ab.setColor(android.graphics.Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
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
