//
//  Server.m
//  Server
//
//  Created by Frederik Riedel on 03.12.14.
//  Copyright (c) 2014 Frederik Riedel. All rights reserved.
//

#import "FRServer.h"

@implementation FRServer


+(void) dataFromURL:(NSString *) url HTTPMethod:(NSString *) HTTPMethod attributes:(NSDictionary *) attributes HTTPHeaderFieldDictionary:(NSDictionary *) HTTPHeaderFields andCallbackBlock: (void (^) (NSData *data)) block {
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        NSMutableURLRequest *request =[NSMutableURLRequest requestWithURL:[NSURL URLWithString:url]];
        [request setHTTPMethod:HTTPMethod];
        
        if(HTTPHeaderFields) {
            for(NSString *key in HTTPHeaderFields) {
                [request setValue:[HTTPHeaderFields valueForKey:key] forHTTPHeaderField:key];
            }
        }
        
        
        if(attributes) {
            NSData* jsonData = [NSJSONSerialization dataWithJSONObject:attributes options:0 error:nil];
            request.HTTPBody = jsonData;
        }
        
        
        NSURLResponse *response;
        NSError *err;
        NSData *responseData = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&err];
        
        dispatch_async(dispatch_get_main_queue(), ^{
            block(responseData);
        });
    });
}


+(void) imageFromURL:(NSString *)url HTTPMethod:(NSString *)HTTPMethod attributes:(NSDictionary *)attributes HTTPHeaderFieldDictionary:(NSDictionary *)HTTPHeaderFields andCallbackBlock:(void (^)(UIImage *))block {
    
    void (^finishedDownloading)(NSData *data) = ^void(NSData *data) {
        if(data) {
            block([UIImage imageWithData:data]);
        } else {
            block(nil);
        }
        
    };
    
    [FRServer dataFromURL:url HTTPMethod:HTTPMethod attributes:attributes HTTPHeaderFieldDictionary:HTTPHeaderFields andCallbackBlock:finishedDownloading];
}


+(void) jsonFromURL:(NSString *)url HTTPMethod:(NSString *)HTTPMethod attributes:(NSDictionary *)attributes HTTPHeaderFieldDictionary:(NSDictionary *)HTTPHeaderFields andCallbackBlock:(void (^)(NSDictionary *))block {
    
    void (^finishedDownloading)(NSData *data) = ^void(NSData *data) {
        if(data) {
            block([NSJSONSerialization JSONObjectWithData:data options:0 error:nil]);
        } else {
            block(nil);
        }
    };
    
    
    [FRServer dataFromURL:url HTTPMethod:HTTPMethod attributes:attributes HTTPHeaderFieldDictionary:HTTPHeaderFields andCallbackBlock:finishedDownloading];
}


@end
