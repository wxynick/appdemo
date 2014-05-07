/**
 * 
 */
package com.example.imageration.ui;

import org.jgroups.JChannel;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.imageration.R;
import com.example.imageration.service.INodeManagementService;
import com.wxxr.mobile.core.microkernel.api.KUtils;

/**
 * @author wangxuyang
 * 
 */
public class NodeManagerMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nm_home);
		ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab config_tab = bar.newTab().setText("配置");
		ActionBar.Tab help_tab = bar.newTab().setText("帮助中心");
		Fragment config_fg = new Fragment(){
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				
				return inflater.inflate(R.layout.config_info, container, false);
			}
		};
		Fragment help_fg = new Fragment(){
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				return inflater.inflate(R.layout.config_info, container, false);
			}
		};
		config_tab.setTabListener(new MyTabsListener(config_fg));
		help_tab.setTabListener(new MyTabsListener(help_fg));
		bar.addTab(config_tab,true);
		bar.addTab(help_tab);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_nodes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		JChannel channel = KUtils.getService(INodeManagementService.class)
				.getChannel();
		switch (item.getItemId()) {
		case R.id.nodelist:
			getActionBar().setTitle(channel.getViewAsString());
			break;
		case R.id.config:
			getActionBar()
					.setTitle(
							channel.getClusterName() + ":"
									+ channel.getAddressAsUUID());
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected class MyTabsListener implements ActionBar.TabListener {
		private Fragment fragment;

		public MyTabsListener(Fragment fragment) {
			this.fragment = fragment;
		}
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ft.add(R.id.fragment_place, fragment, null);

		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.detach(fragment);
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			ft.add(R.id.fragment_place, fragment, null);
		}
	}
}
