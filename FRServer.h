//
//  Server.h
//  Server
//
//  Created by Frederik Riedel on 03.12.14.
//  Copyright (c) 2014 Frederik Riedel. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FRServerRequest.h"
@import UIKit;
@interface FRServer : NSObject


+(void) dataFromServerRequest:(FRServerRequest *) serverRequest;
+(void) imageFromServerRequest:(FRServerRequest *) serverRequest;
+(void) jsonFromServerRequest:(FRServerRequest *) serverRequest;
+(void) stringFromServerRequest:(FRServerRequest *) serverRequest;


@end