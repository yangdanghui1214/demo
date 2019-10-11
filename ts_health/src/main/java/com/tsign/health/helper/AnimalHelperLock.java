package com.tsign.health.helper;

import com.tsign.health.lock.ObjWithLock;
import com.tsign.health.model.AttendAnimal;
import com.tsign.health.model.HttpRequestModel;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 健康数据 上传操作类
 *
 * @author 13001
 */
public class AnimalHelperLock extends ObjWithLock<AnimalHelper> {


    public AnimalHelperLock(AnimalHelper obj) {
        super(obj);
    }

    public AnimalHelperLock(AnimalHelper obj, ReentrantReadWriteLock lock) {
        super(obj, lock);
    }

    /**
     * @return 获取接口数据
     */
    public List<AttendAnimal.DetailsBean> get() {
        ReentrantReadWriteLock.ReadLock readLock = this.getLock().readLock();
        readLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            return dataHelper.getAttendacne();
        } catch (Throwable e) {

        } finally {
            readLock.unlock();
        }
        return null;
    }

    /**
     * @return 获取有 RequestNum 的接口数据
     */
    public String getSerialRequestNum() {
        ReentrantReadWriteLock.ReadLock readLock = this.getLock().readLock();
        readLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            return dataHelper.getSerialRequestNum();
        } catch (Throwable e) {
            //log.error(e.getMessage(), e);
        } finally {
            readLock.unlock();
        }
        return null;
    }

    /**
     * 根据 requstNum 获取健康数据
     *
     * @param requstNum
     * @return
     */
    public List<AttendAnimal.DetailsBean> get(String requstNum) {
        ReentrantReadWriteLock.ReadLock readLock = this.getLock().readLock();
        readLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            return dataHelper.getAttendacne(requstNum);
        } catch (Throwable e) {
            //log.error(e.getMessage(), e);
        } finally {
            readLock.unlock();
        }
        return null;
    }

    /**
     * 保存健康数据
     *
     * @param attendences
     */
    public void putAttendance(List<AttendAnimal.DetailsBean> attendences) {
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            dataHelper.putAttendacne(attendences);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 更新健康数据
     * @param attendences
     */
    public void update(List<AttendAnimal.DetailsBean> attendences) {
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            dataHelper.updateAttendacne(attendences);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 批量删除健康数据
     *
     * @param detailsBeans
     */
    public long delete(List<AttendAnimal.DetailsBean> detailsBeans) {
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            return dataHelper.deleteAttendance(detailsBeans);
        } catch (Exception e) {
            return 0L;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 根据 requstNum 来删除数据
     *
     * @param requstNum
     */
    public long delete(String requstNum, String tag) {
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            return dataHelper.deleteAttendance(requstNum, tag);
        } catch (Exception e) {
            return 0L;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 保存请求失败数据
     *
     * @param httpRequestModel
     */
    public void putHttpRequestLogBean(HttpRequestModel httpRequestModel) {
        ReentrantReadWriteLock.WriteLock writeLock = this.getLock().writeLock();
        writeLock.lock();
        try {
            AnimalHelper dataHelper = this.getObj();
            dataHelper.putHttpRequestLogBean(httpRequestModel);
        } finally {
            writeLock.unlock();
        }
    }

}
