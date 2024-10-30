
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNAdditionalpackageSpec.h"

@interface Additionalpackage : NSObject <NativeAdditionalpackageSpec>
#else
#import <React/RCTBridgeModule.h>

@interface Additionalpackage : NSObject <RCTBridgeModule>
#endif

@end
