package com.example.imageration;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Application;
import android.os.Environment;

import com.example.imageration.service.impl.NodeManagementServiceImpl;
import com.wxxr.mobile.android.app.AndroidFramework;
import com.wxxr.mobile.android.app.IAndroidAppContext;
import com.wxxr.mobile.android.app.IAndroidFramework;
import com.wxxr.mobile.android.http.HttpRpcServiceModule;
import com.wxxr.mobile.core.microkernel.api.AbstractModule;

public class AppFramework	extends		AndroidFramework<IExampleAppContext, AbstractModule<IExampleAppContext>> {

	private final MobileApplication app;

	IAndroidAppContext a;

	private class ExampleAppContextImpl extends AbstractContext implements
			IExampleAppContext {
		private ExecutorService executor = Executors.newSingleThreadExecutor();

		@SuppressWarnings("rawtypes")
		@Override
		public IAndroidFramework getApplication() {
			return AppFramework.this;
		}

		@Override
		public void invokeLater(Runnable arg0) {
			runOnUIThread(arg0, 0, null);
		}

	};

	private ExampleAppContextImpl context;

	public AppFramework(MobileApplication application) {
		this.app = application;
	}

	@Override
	public Application getAndroidApplication() {
		return app;
	}

	@Override
	public String getApplicationName() {
		return "Android study Example";
	}

	@Override
	protected IExampleAppContext getContext() {
		if (context == null)
			context = new ExampleAppContextImpl();
		return context;
	}

	@Override
	protected void initModules() {
		registerKernelModule(new NodeManagementServiceImpl());
		/*
		 * registerKernelModule(new
		 * PreferenceManagerModule<ICallHeplerAppContext>());
		 * RestEasyClientModule<ICallHeplerAppContext> rest = new
		 * RestEasyClientModule<ICallHeplerAppContext>();
		 * rest.getClient().register(JacksonJsonProvider.class);
		 * rest.getClient().register(XStreamProvider.class);
		 * rest.getClient().register(GSONProvider.class);
		 * rest.getClient().register(StringTextStar.class);
		 * registerKernelModule(rest);
		 * 
		 * CommandExecutorModule<ICallHeplerAppContext> cmdExecutor = new
		 * CommandExecutorModule<ICallHeplerAppContext>();
		 * registerKernelModule(cmdExecutor);
		 */

		HttpRpcServiceModule<IExampleAppContext> m = new HttpRpcServiceModule<IExampleAppContext>();
		m.setEnablegzip(false);
		m.setConnectionPoolSize(30);
		registerKernelModule(m);

		/*
		 * registerKernelModule(new AppSiteSecurityModule());
		 * registerKernelModule(new
		 * NetworkManagementModule<ICallHeplerAppContext>());
		 * registerKernelModule(new MicoNewsModleService());
		 * 
		 * 
		 * 
		 * registerKernelModule(new
		 * SimpleUserIdentityManagerModule<ICallHeplerAppContext>());
		 * registerKernelModule(new
		 * UserBasedSessionManagerModule<ICallHeplerAppContext>());
		 * 
		 * registerKernelModule(new TimeService()); registerKernelModule(new
		 * WorkbenchManagerModule());
		 * 
		 * registerKernelModule(new ClientConfigManagerService());
		 * registerKernelModule(new ClientCustomModule());
		 * registerKernelModule(new FeedBackService()); registerKernelModule(new
		 * DXHZService()); registerKernelModule(new UserService());
		 * registerKernelModule(new MissCallService()); registerKernelModule(new
		 * AffiliationAreaService()); registerKernelModule(new
		 * HistoryDataImportService()); registerKernelModule(new
		 * MobileSupportService()); registerKernelModule(new
		 * PhoneSystemService()); registerKernelModule(new PrivateSMService());
		 * registerKernelModule(new PrivateSimiBindingEmailService());
		 * registerKernelModule(new SmsContentParseModule());
		 * registerKernelModule(new SMSInterceptService());
		 * 
		 * registerKernelModule(new CallRecorderService());
		 * registerKernelModule(new ServiceWrap()); registerKernelModule(new
		 * MoblieBusinessService()); registerKernelModule(new
		 * OfficeLineHtmlProvideService());
		 * 
		 * registerKernelModule(new SecurityServiceModule());
		 * registerKernelModule(new CHMTreeSyncConnector());
		 * registerKernelModule(new MTreeSyncClientModule());
		 * registerKernelModule(new ClientConfigManagerService());
		 * registerKernelModule(new ContentManager()); registerKernelModule(new
		 * SSHXMsgManagementModule()); registerKernelModule(new
		 * PiggybackService()); registerKernelModule(new RuleEngineService());
		 * registerKernelModule(new HtmlMessageManager());
		 * registerKernelModule(new MessageProviderModule());
		 * registerKernelModule(new ShareContextService());
		 */
	}

	public File getDataDir(String name, int mode) {
		File dataDir;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			dataDir = new File(Environment.getExternalStorageDirectory(), name);
		} else {
			dataDir = getAndroidApplication().getDir(name, mode);
		}
		return dataDir;
	}
}
