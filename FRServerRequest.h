//
//  FRServerRequest.h
//  Server
//
//  Created by Frederik Riedel on 04.12.14.
//  Copyright (c) 2014 Frederik Riedel. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface FRServerRequest : NSObject

@property(readwrite) NSString *url;
@property(readwrite) NSString *HTTPMethod;
@property(readwrite) NSDictionary *attributes;
@property(readwrite) NSDictionary *HTTPHeaderFields;
@property(readwrite) void (^block) (NSObject *data);



@end
