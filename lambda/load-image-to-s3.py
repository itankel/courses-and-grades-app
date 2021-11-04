import json
import base64
import boto3

s3 = boto3.resource('s3')
bucket_name = 'images-to-store'

def lambda_handler(event, context):
    
    print("event is >"+ json.dumps(event))
    if ( event["httpMethod"] =="GET"):
         return getSimpleResponse()
    if ( event["httpMethod"] =="POST"):
        body_in=event["body"]
        print(body_in)
        body_from_http=json.loads(body_in)
        return load_images(body_from_http)
    return {
        'statusCode': 200,
        'body': json.dumps('not supported yet')
    }
    
    
def getSimpleResponse():
    return {
        'statusCode': 200,
        'body': json.dumps('Hello I got your get!')
    }

    
def load_images(body_from_http):
    #print("body_from_http >" + body_from_http)
    event_body_image_format=body_from_http["image_format"]
    print("event_body_image_format >" + event_body_image_format)
    print(type(event_body_image_format))
    event_body_course_name=body_from_http["course_name"]
    print("event_body_course_name >" + event_body_course_name)
    print(type(event_body_course_name))
    event_body_course_image_in_base64=body_from_http["course_image"]
    print(" event_body_course_image_in_base64 >" + event_body_course_image_in_base64)
    print(type(event_body_course_image_in_base64))
    print("after type")
    
    file_name_with_extention=event_body_course_name+"."+event_body_image_format
    obj = s3.Object(bucket_name,file_name_with_extention)
    obj.put(Body=base64.b64decode(event_body_course_image_in_base64),ACL='public-read')
    print("after put")
    location = boto3.client('s3').get_bucket_location(Bucket=bucket_name)['LocationConstraint']
    print("locationis >" +str(location))
    if (location is None):
       location="" 
    #get object url
    object_url = "https://%s.s3%s.amazonaws.com/%s" % (bucket_name,location, file_name_with_extention)
    print("Object url>"+object_url)
    return {
            'headers': { "Content-type": "text/html" },
            'statusCode': 200,
            'body': object_url 
    }
 
    
 