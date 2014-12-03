//
//  Server.h
//  Server
//
//  Created by Frederik Riedel on 03.12.14.
//  Copyright (c) 2014 Frederik Riedel. All rights reserved.
//

#import <Foundation/Foundation.h>
@import UIKit;
@interface FRServer : NSObject


+(void) dataFromURL:(NSString *) url HTTPMethod:(NSString *) HTTPMethod attributes:(NSDictionary *) attributes HTTPHeaderFieldDictionary:(NSDictionary *) HTTPHeaderFields andCallbackBlock: (void (^) (NSData *data)) block;

+(void) imageFromURL:(NSString *) url HTTPMethod:(NSString *) HTTPMethod attributes:(NSDictionary *) attributes HTTPHeaderFieldDictionary:(NSDictionary *) HTTPHeaderFields andCallbackBlock: (void (^) (UIImage *image)) block;

+(void) jsonFromURL:(NSString *) url HTTPMethod:(NSString *) HTTPMethod attributes:(NSDictionary *) attributes HTTPHeaderFieldDictionary:(NSDictionary *) HTTPHeaderFields andCallbackBlock: (void (^) (NSDictionary *JSON)) block;



@end