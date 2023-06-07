import SwiftUI
import shared

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            NavigationView{
                SplashScreen()
            }
		}
	}
    
    init(){
        AppKoinKt.doInitKoin()
    }
}
