import Sidebar from '../components/SideBar';
import GridCard from '../components/GridCard';

export default function Projects() {
    // Retorno dos componentes
    return (
        <div style={{display: 'flex', width: '100vw', height: '100vh'}}>
            <Sidebar/>
            <GridCard/>
        </div>
    );
}
