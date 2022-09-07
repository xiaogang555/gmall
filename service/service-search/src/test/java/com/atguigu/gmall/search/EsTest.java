package com.atguigu.gmall.search;


import com.atguigu.gmall.search.bean.Person;
import com.atguigu.gmall.search.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EsTest {

    @Resource
    PersonRepository personRepository;//简单查

    @Resource
    ElasticsearchRestTemplate elasticsearchRestTemplate;//复杂查询

    @Test
    public void test(){
        Person person1 = new Person();
        person1.setId(1L);
        person1.setFirstName("三555");
        person1.setLastName("张");
        person1.setAge(19);
        person1.setAddress("北京市昌平区");

        Person person = new Person();
        person.setId(2L);
        person.setFirstName("三");
        person.setLastName("张");
        person.setAge(19);
        person.setAddress("北京市朝阳区");

        Person person2 = new Person();
        person2.setId(3L);
        person2.setFirstName("四");
        person2.setLastName("力");
        person2.setAge(19);
        person2.setAddress("上海市松江区");


        Person person3 = new Person();
        person3.setId(4L);
        person3.setFirstName("三2");
        person3.setLastName("张");
        person3.setAge(20);
        person3.setAddress("北京市天安门");

        personRepository.save(person1);
        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);

        System.out.println("完成...");
    }

    @Test
    public void query(){


        //简单的以及自定义好了
//        Optional<Person> byId = personRepository.findById(2L);
//        System.out.println(byId);

        //复杂的起名
        List<Person> like = personRepository.findAllByAddressLike("北京市");
        System.out.println(like);

    }
}
